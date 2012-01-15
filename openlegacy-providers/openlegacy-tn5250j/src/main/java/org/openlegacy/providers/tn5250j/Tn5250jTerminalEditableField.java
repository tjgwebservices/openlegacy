package org.openlegacy.providers.tn5250j;

import org.openlegacy.terminal.support.SimpleTerminalPosition;
import org.tn5250j.framework.tn5250.ScreenField;

public class Tn5250jTerminalEditableField extends Tn5250jTerminalField {

	private static final long serialVersionUID = 1L;

	private ScreenField screenField;

	public Tn5250jTerminalEditableField(ScreenField screenField) {
		super(screenField.getString(), new SimpleTerminalPosition(screenField.startRow() + 1, screenField.startCol() + 1),
				screenField.getLength());
		this.screenField = screenField;
	}

	@Override
	public String getValue() {
		if (getModifiedValue() != null) {
			return getModifiedValue();
		}
		return screenField.getString();
	}

	@Override
	public int getLength() {
		return screenField.getLength();
	}

	@Override
	public boolean isEditable() {
		return true;
	}

}
