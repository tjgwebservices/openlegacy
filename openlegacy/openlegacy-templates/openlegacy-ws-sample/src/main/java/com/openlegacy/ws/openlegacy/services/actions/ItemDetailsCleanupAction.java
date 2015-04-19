package com.openlegacy.ws.openlegacy.services.actions;

import org.openlegacy.terminal.TerminalSession;
import org.openlegacy.terminal.actions.TerminalAction;
import org.openlegacy.terminal.actions.TerminalActions;


public class ItemDetailsCleanupAction implements TerminalAction {
		
		public void perform(TerminalSession terminalSession, Object entity, Object... keys){
			terminalSession.doAction(TerminalActions.F12());
			// PLACE HOLDER for cleanup action
		}

		@Override
		public boolean isMacro() {
			// TODO Auto-generated method stub
			return false;
		}
}