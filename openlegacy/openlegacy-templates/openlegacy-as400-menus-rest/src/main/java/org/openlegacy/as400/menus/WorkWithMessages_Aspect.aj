// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.as400.menus;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.Color;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

privileged @SuppressWarnings("unused") aspect WorkWithMessages_Aspect {

    declare parents: WorkWithMessages implements ScreenEntity;
    private String WorkWithMessages.focusField;
    private List<TerminalActionDefinition> WorkWithMessages.actions = new ArrayList<TerminalActionDefinition>();
    
	

	

    private List<TerminalActionDefinition> WorkWithMessages.workWithMessagesRecordsActions = new ArrayList<TerminalActionDefinition>();
    

    public String WorkWithMessages.getMessagesFor(){
    	return this.messagesFor;
    }
    

    public List<WorkWithMessagesRecord> WorkWithMessages.getWorkWithMessagesRecords(){
    	return this.workWithMessagesRecords;
    }
    

    public List<TerminalActionDefinition> WorkWithMessages.getWorkWithMessagesRecordsActions(){
    	return this.workWithMessagesRecordsActions;
    }

    public String WorkWithMessages.getFocusField(){
    	return focusField;
    }
    public void WorkWithMessages.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
    public List<TerminalActionDefinition> WorkWithMessages.getActions(){
    	return actions;
    }
    
}