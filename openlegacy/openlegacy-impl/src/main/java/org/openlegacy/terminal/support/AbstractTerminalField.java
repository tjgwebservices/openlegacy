package org.openlegacy.terminal.support;

import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.utils.TerminalEqualsHashcodeUtil;
import org.openlegacy.utils.StringUtil;

public abstract class AbstractTerminalField implements TerminalField {

	private static final long serialVersionUID = 1L;
	private String modifiedValue;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TerminalField)) {
			return false;
		}
		TerminalField otherField = (TerminalField)obj;
		return TerminalEqualsHashcodeUtil.fieldEquals(this, otherField);
	}

	@Override
	public int hashCode() {
		return TerminalEqualsHashcodeUtil.fieldHashCode(this);
	}

	@Override
	public String toString() {
		return SnapshotUtils.fieldToString(this);
	}

	public boolean isEmpty() {
		return StringUtil.isEmpty(getValue());
	}

	public boolean isPassword() {
		return isEditable() && isHidden();
	}

	public void setValue(String value) {
		modifiedValue = value;
	}

	public boolean isModified() {
		return modifiedValue != null;
	}

	public String getModifiedValue() {
		return modifiedValue;
	}
}
