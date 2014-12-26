package org.openlegacy.designtime.db.generators.support;

import org.apache.commons.lang.CharEncoding;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.db.definitions.DbEntityDefinition;
import org.openlegacy.db.definitions.DbFieldDefinition;
import org.openlegacy.db.definitions.SimpleDbActionDefinition;
import org.openlegacy.db.definitions.SimpleDbColumnFieldDefinition;
import org.openlegacy.definitions.ActionDefinition;
import org.openlegacy.designtime.db.generators.DbPojoCodeModel;
import org.openlegacy.designtime.db.generators.support.DefaultDbPojoCodeModel.Action;
import org.openlegacy.designtime.db.generators.support.DefaultDbPojoCodeModel.ColumnField;
import org.openlegacy.designtime.utils.JavaParserUtil;
import org.openlegacy.exceptions.EntityNotAccessibleException;
import org.openlegacy.utils.StringUtil;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.AnnotationExpr;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ivan Bort
 * 
 */
public class DbCodeBasedDefinitionUtils {

	private final static Log logger = LogFactory.getLog(DbCodeBasedDefinitionUtils.class);

	public static DbEntityDefinition getEntityDefinition(CompilationUnit compilationUnit, File packageDir) {
		CodeBasedDbEntityDefinition entityDefinition = null;

		List<TypeDeclaration> types = compilationUnit.getTypes();
		TypeDeclaration type = compilationUnit.getTypes().get(0);
		List<BodyDeclaration> members = type.getMembers();
		for (BodyDeclaration bodyDeclaration : members) {
			// look for inner classes
			if (bodyDeclaration instanceof ClassOrInterfaceDeclaration) {
				types.add((TypeDeclaration)bodyDeclaration);
			}
		}

		for (TypeDeclaration typeDeclaration : types) {
			List<AnnotationExpr> annotations = typeDeclaration.getAnnotations();
			if (annotations == null) {
				continue;
			}
			for (AnnotationExpr annotationExpr : annotations) {
				DbPojoCodeModel dbPojoCodeModel = null;
				if (JavaParserUtil.hasAnnotation(annotationExpr, DbAnnotationConstants.DB_JPA_ENTITY_ANNOTATION)
						|| JavaParserUtil.hasAnnotation(annotationExpr, DbAnnotationConstants.DB_ENTITY_ANNOTATION)
						|| JavaParserUtil.hasAnnotation(annotationExpr, DbAnnotationConstants.DB_ENTITY_SUPER_CLASS_ANNOTATION)) {
					dbPojoCodeModel = new DefaultDbPojoCodeModel(compilationUnit, (ClassOrInterfaceDeclaration)typeDeclaration,
							typeDeclaration.getName(), null);
					entityDefinition = new CodeBasedDbEntityDefinition(dbPojoCodeModel, packageDir);
					return entityDefinition;
				}
			}
		}
		return entityDefinition;
	}

	public static Map<String, DbFieldDefinition> getColumnFieldsFromCodeModel(DbPojoCodeModel codeModel) {
		Collection<ColumnField> columnFields = codeModel.getColumnFields().values();

		Map<String, DbFieldDefinition> fieldDefinitions = new TreeMap<String, DbFieldDefinition>();
		for (ColumnField javaColumnField : columnFields) {
			SimpleDbColumnFieldDefinition definition = new SimpleDbColumnFieldDefinition(javaColumnField.getFieldName(),
					javaColumnField.getFieldTypeArgs());
			definition.setJavaTypeName(javaColumnField.getFieldType());
			definition.setNameAttr(javaColumnField.getName());
			definition.setUnique(javaColumnField.isUnique());
			definition.setNullable(javaColumnField.isNullable());
			definition.setInsertable(javaColumnField.isInsertable());
			definition.setUpdatable(javaColumnField.isUpdatable());
			definition.setColumnDefinition(javaColumnField.getColumnDefinition());
			definition.setTable(javaColumnField.getTable());
			definition.setLength(javaColumnField.getLength());
			definition.setPrecision(javaColumnField.getPrecision());
			definition.setScale(javaColumnField.getScale());
			definition.setOneToManyDefinition(javaColumnField.getOneToManyDefinition());
			definition.setKey(javaColumnField.isKey());

			definition.setDisplayName(javaColumnField.getDisplayName());
			definition.setPassword(javaColumnField.isPassword());
			definition.setSampleValue(javaColumnField.getSampleValue());
			definition.setDefaultValue(javaColumnField.getDefaultValue());
			definition.setHelpText(javaColumnField.getHelpText());
			definition.setRightToLeft(javaColumnField.isRightToLeft());
			definition.setInternal(javaColumnField.isInternal());
			definition.setMainDisplayField(javaColumnField.isMainDisplayField());

			fieldDefinitions.put(javaColumnField.getFieldName(), definition);
		}

		return fieldDefinitions;
	}

	public static List<ActionDefinition> getActionsFromCodeModel(DbPojoCodeModel codeModel, File packageDir) {
		List<Action> actions = codeModel.getActions();
		List<ActionDefinition> actionDefinitions = new ArrayList<ActionDefinition>();
		for (Action action : actions) {
			String actionName = StringUtil.toClassName(action.getActionName());
			SimpleDbActionDefinition actionDefinition = new SimpleDbActionDefinition(actionName, action.getDisplayName());
			actionDefinition.setGlobal(action.isGlobal());
			if (action.getAlias() != null) {
				actionDefinition.setAlias(action.getAlias());
			}
			if (action.getTargetEntityName() != null) {
				DbEntityDefinition targetDefinition = getEntityDefinition(action.getTargetEntityName(), packageDir);
				actionDefinition.setTargetEntityDefinition(targetDefinition);
				actionDefinition.setTargetEntityName(action.getTargetEntityName());
			}

			actionDefinitions.add(actionDefinition);
		}
		return actionDefinitions;
	}

	private static DbEntityDefinition getEntityDefinition(String entityName, File packageDir) {
		File entityFile = new File(packageDir, entityName + ".java");
		if (!entityFile.exists()) {
			logger.debug(MessageFormat.format("Source file for entity {0} is not defined. Unable to find file {1}",
					entityFile.getName(), entityFile.getAbsoluteFile()));
		}
		CompilationUnit compilationUnit = null;
		try {
			compilationUnit = JavaParser.parse(entityFile, CharEncoding.UTF_8);
		} catch (ParseException e) {
			logger.warn("Failed parsing java file:" + e.getMessage());
		} catch (IOException e) {
			throw (new EntityNotAccessibleException(e));
		}
		return getEntityDefinition(compilationUnit, packageDir);
	}

}
