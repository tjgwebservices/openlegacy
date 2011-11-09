package org.openlegacy.terminal.spi;

import org.openlegacy.HostAction;
import org.openlegacy.exceptions.HostEntityNotFoundException;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.TerminalScreen;
import org.openlegacy.terminal.exceptions.ScreenEntityNotAccessibleException;

/**
 * Define a binder API for building a screen entity from a terminalScreen and collecting fields from a given screenEntity
 * 
 */
public interface ScreenEntityBinder {

	<S extends ScreenEntity> S buildScreenEntity(TerminalScreen hostScreen);

	<T> T buildScreenEntity(Class<T> screenEntityClass, TerminalScreen hostScreen) throws HostEntityNotFoundException,
			ScreenEntityNotAccessibleException;

	TerminalSendAction buildSendFields(TerminalScreen terminalScreen, HostAction hostAction, ScreenEntity screenEntity);

}
