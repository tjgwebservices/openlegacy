package org.openlegacy.terminal.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.FetchMode;
import org.openlegacy.exceptions.HostEntityNotFoundException;
import org.openlegacy.terminal.ChildScreensDefinitionProvider;
import org.openlegacy.terminal.FieldMappingsDefinitionProvider;
import org.openlegacy.terminal.ScreenPosition;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalScreen;
import org.openlegacy.terminal.TerminalSession;
import org.openlegacy.terminal.definitions.ChildScreenDefinition;
import org.openlegacy.terminal.definitions.FieldMappingDefinition;
import org.openlegacy.terminal.exceptions.ScreenEntityNotAccessibleException;
import org.openlegacy.terminal.spi.ScreenEntityBinder;
import org.openlegacy.terminal.spi.ScreensRecognizer;
import org.openlegacy.terminal.utils.ScreenAccessUtils;
import org.openlegacy.terminal.utils.ScreenEntityDirectFieldAccessor;
import org.openlegacy.terminal.utils.ScreenNavigationUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Data binder implementation for building a screenEntity from a given terminal screen, and build fields to send from a given
 * screenEntity
 * 
 */
@Scope("sesssion")
// since performing action on terminalSession
public class DefaultScreenEntityBinder implements ScreenEntityBinder {

	@Inject
	private ScreensRecognizer screensRecognizer;

	@Inject
	private FieldMappingsDefinitionProvider fieldMappingsProvider;

	@Inject
	private ChildScreensDefinitionProvider childScreensDefinitionProvider;

	@Inject
	private ApplicationContext applicationContext;

	@Inject
	private TerminalSession terminalSession;

	private final static Log logger = LogFactory.getLog(DefaultScreenEntityBinder.class);

	private static final String TERMINAL_SCREEN = "terminalScreen";

	@SuppressWarnings("unchecked")
	public <T> T buildScreenEntity(Class<T> screenEntity, TerminalScreen terminalScreen) throws HostEntityNotFoundException,
			ScreenEntityNotAccessibleException {

		Class<?> matchedScreenEntity = screensRecognizer.match(terminalScreen);

		ScreenNavigationUtil.validateCurrentScreen(screenEntity, matchedScreenEntity);

		return (T)buildScreenEntityInner(matchedScreenEntity, terminalScreen, true);
	}

	public Object buildScreenEntity(TerminalScreen terminalScreen, boolean deep) {
		Class<?> matchedScreenEntity = screensRecognizer.match(terminalScreen);
		return buildScreenEntityInner(matchedScreenEntity, terminalScreen, deep);
	}

	private Object buildScreenEntityInner(Class<?> screenEntityClass, TerminalScreen terminalScreen, boolean deep) {
		try {
			Object screenEntity = applicationContext.getBean(screenEntityClass);

			ScreenEntityDirectFieldAccessor fieldAccessor = new ScreenEntityDirectFieldAccessor(screenEntity);

			if (fieldAccessor.isWritableProperty(TERMINAL_SCREEN)) {
				fieldAccessor.setFieldValue(TERMINAL_SCREEN, terminalScreen);

			}

			injectFields(fieldAccessor, screenEntityClass, terminalScreen);

			if (deep) {
				injectChildScreens(fieldAccessor, screenEntityClass, terminalScreen);
			}

			return screenEntity;
		} catch (Exception e) {
			throw (new IllegalArgumentException(e));
		}

	}

	private static TerminalField extractTerminalField(final TerminalScreen terminalScreen, FieldMappingDefinition fieldMapping) {
		TerminalField terminalField = terminalScreen.getField(fieldMapping.getScreenPosition());
		return terminalField;
	}

	private void injectFields(ScreenEntityDirectFieldAccessor fieldAccessor, Class<?> screenEntity,
			final TerminalScreen terminalScreen) throws Exception {

		Collection<FieldMappingDefinition> fieldMappingDefinitions = fieldMappingsProvider.getFieldsMappingDefinitions(
				terminalScreen, screenEntity);

		for (FieldMappingDefinition fieldMappingDefinition : fieldMappingDefinitions) {

			TerminalField terminalField = extractTerminalField(terminalScreen, fieldMappingDefinition);

			String fieldName = fieldMappingDefinition.getName();
			if (fieldAccessor.isWritableProperty(fieldName)) {
				fieldAccessor.setTerminalField(fieldName, terminalField);
			}

		}

	}

	private void injectChildScreens(ScreenEntityDirectFieldAccessor fieldAccessor, Class<?> screenEntity,
			final TerminalScreen terminalScreen) throws Exception {

		Collection<ChildScreenDefinition> childScreenDefinitions = childScreensDefinitionProvider.getChildScreenDefinitions(screenEntity);

		for (ChildScreenDefinition childScreenDefinition : childScreenDefinitions) {

			if (childScreenDefinition.getFetchMode() == FetchMode.LAZY) {
				continue;
			}

			String fieldName = childScreenDefinition.getFieldName();

			Class<?> fieldType = fieldAccessor.getFieldType(fieldName);
			Object childScreen = ScreenAccessUtils.getChildScreen(terminalSession, fieldType, childScreenDefinition);

			fieldAccessor.setFieldValue(fieldName, childScreen);
		}

	}

	public Map<ScreenPosition, String> buildSendFields(TerminalScreen terminalScreen, Object screenEntity) {
		Map<ScreenPosition, String> fieldValues = new LinkedHashMap<ScreenPosition, String>();

		if (screenEntity == null) {
			return fieldValues;
		}

		Collection<FieldMappingDefinition> fieldsInfo = fieldMappingsProvider.getFieldsMappingDefinitions(terminalScreen,
				screenEntity.getClass());

		if (fieldsInfo == null) {
			return fieldValues;
		}

		ScreenEntityDirectFieldAccessor fieldAccessor = new ScreenEntityDirectFieldAccessor(screenEntity);
		for (FieldMappingDefinition fieldMapping : fieldsInfo) {

			if (fieldMapping.isEditable()) {
				Object value = fieldAccessor.getFieldValue(fieldMapping.getName());
				ScreenPosition hostScreenPosition = fieldMapping.getScreenPosition();

				if (value != null) {
					fieldValues.put(hostScreenPosition, value.toString());
					if (logger.isDebugEnabled()) {
						logger.debug(MessageFormat.format(
								"Field {0} was set with value \"{1}\" to send fields for screenEntity {2}",
								fieldMapping.getName(), value, screenEntity));
					}
				}
			}

		}
		return fieldValues;
	}

}
