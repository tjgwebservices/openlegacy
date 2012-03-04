package org.openlegacy.terminal.definitions;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.openlegacy.FieldType;
import org.openlegacy.definitions.support.AbstractFieldDefinition;
import org.openlegacy.terminal.TerminalPosition;
import org.openlegacy.terminal.support.SnapshotUtils;

/**
 * Defines a mapping between a screenEntity java field name and it's screen position and length
 * 
 */
public class SimpleScreenFieldDefinition extends AbstractFieldDefinition<ScreenFieldDefinition> implements ScreenFieldDefinition {

	private TerminalPosition position = null;
	private int length;
	private boolean editable;
	private String sampleValue;
	private Class<?> javaType;
	private TerminalPosition labelPosition;

	public SimpleScreenFieldDefinition(String name, Class<? extends FieldType> fieldType) {
		super(name, fieldType);
	}

	public TerminalPosition getPosition() {
		return position;
	}

	public void setPosition(TerminalPosition position) {
		this.position = position;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getSampleValue() {
		return sampleValue;
	}

	public void setSampleValue(String sampleValue) {
		this.sampleValue = sampleValue;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int compareTo(ScreenFieldDefinition o) {
		return SnapshotUtils.comparePositions(this.getPosition(), o.getPosition());
	}

	public TerminalPosition getEndPosition() {
		return SnapshotUtils.getEndPosition(getPosition(), getLength());
	}

	public Class<?> getJavaType() {
		return javaType;
	}

	public String getJavaTypeName() {
		return javaType.getSimpleName();
	}

	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}

	public TerminalPosition getLabelPosition() {
		return labelPosition;
	}

	public void setLabelPosition(TerminalPosition labelPosition) {
		this.labelPosition = labelPosition;
	}

}
