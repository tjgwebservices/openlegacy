package org.openlegacy.terminal;

import org.openlegacy.HostSessionModule;
import org.openlegacy.terminal.spi.TerminalSendAction;

public interface TerminalSessionModule extends HostSessionModule {

	void beforeSendAction(TerminalConnection terminalConnection, TerminalSendAction terminalSendAction);

	void afterSendAction(TerminalConnection terminalConnection);
}
