package org.openlegacy.terminal.persistance;

import org.apache.commons.beanutils.BeanUtils;
import org.openlegacy.terminal.ScreenPosition;
import org.openlegacy.terminal.TerminalField;
import org.openlegacy.terminal.TerminalRow;
import org.openlegacy.terminal.TerminalScreen;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.spi.TerminalSendAction;
import org.openlegacy.terminal.support.ScreenPositionBean;
import org.openlegacy.terminal.support.TerminalIncomingSnapshot;
import org.openlegacy.terminal.support.TerminalOutgoingSnapshot;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SnapshotPersistanceDTO {

	public static TerminalPersistedSnapshot transformSnapshot(TerminalSnapshot snapshot) {
		try {
			if (snapshot instanceof TerminalIncomingSnapshot) {
				return transformIncomingSnapshot((TerminalIncomingSnapshot)snapshot);
			}
			if (snapshot instanceof TerminalOutgoingSnapshot) {
				return transformOutgoingSnapshot((TerminalOutgoingSnapshot)snapshot);
			}
		} catch (IllegalAccessException e) {
			throw (new IllegalStateException(e));
		} catch (InvocationTargetException e) {
			throw (new IllegalStateException(e));
		}
		throw (new IllegalStateException("Incorrect Terminal snapshot type:" + snapshot.getClass()
				+ ". Only TerminalIncomingSnapshot, TerminalOutgoingSnapshot are allowed"));

	}

	public static TerminalPersistedSnapshot transformIncomingSnapshot(TerminalIncomingSnapshot snapshot)
			throws IllegalAccessException, InvocationTargetException {
		TerminalPersistedSnapshot persistedSnapshot = new TerminalPersistedSnapshot();
		BeanUtils.copyProperties(persistedSnapshot, snapshot);

		TerminalScreen screen = snapshot.getTerminalScreen();

		transformCommonSnapshot(persistedSnapshot, screen);

		return persistedSnapshot;
	}

	private static TerminalPersistedSnapshot transformOutgoingSnapshot(TerminalOutgoingSnapshot snapshot)
			throws IllegalAccessException, InvocationTargetException {
		TerminalPersistedSnapshot persistedSnapshot = new TerminalPersistedSnapshot();
		persistedSnapshot.setSnapshotType(snapshot.getSnapshotType());

		TerminalScreen screen = snapshot.getTerminalScreen();

		transformCommonSnapshot(persistedSnapshot, screen);

		TerminalSendAction sendAction = snapshot.getTerminalSendAction();
		Map<ScreenPosition, String> fields = sendAction.getFields();
		Set<ScreenPosition> fieldPositions = fields.keySet();
		for (ScreenPosition fieldPosition : fieldPositions) {
			TerminalPersistedRow row = (TerminalPersistedRow)persistedSnapshot.getRows().get(fieldPosition.getRow() - 1);
			TerminalPersistedField field = (TerminalPersistedField)row.getField(fieldPosition.getColumn());
			field.setValue(fields.get(fieldPosition));
			field.setModified(true);
		}
		return persistedSnapshot;
	}

	private static TerminalSnapshot transformCommonSnapshot(TerminalPersistedSnapshot persistedSnapshot, TerminalScreen screen)
			throws IllegalAccessException, InvocationTargetException {

		List<TerminalRow> rows = screen.getRows();
		for (TerminalRow terminalRow : rows) {
			TerminalPersistedRow persistedRow = new TerminalPersistedRow();
			BeanUtils.copyProperties(persistedRow, terminalRow);

			List<TerminalField> fields = terminalRow.getFields();
			for (TerminalField terminalField : fields) {
				TerminalPersistedField persistedField = new TerminalPersistedField();
				BeanUtils.copyProperties(persistedField, terminalField);
				persistedField.setScreenPosition(ScreenPositionBean.newInstance(terminalField.getPosition()));
				persistedRow.getFields().add(persistedField);
			}
			persistedSnapshot.getRows().add(persistedRow);
		}
		return persistedSnapshot;
	}
}
