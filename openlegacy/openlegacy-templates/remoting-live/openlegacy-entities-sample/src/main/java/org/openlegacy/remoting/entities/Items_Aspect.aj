// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.remoting.entities;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.Color;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

privileged @SuppressWarnings("unused") aspect Items_Aspect {

    declare parents: Items implements ScreenEntity;
    private String Items.focusField;
    private List<TerminalActionDefinition> Items.actions = new ArrayList<TerminalActionDefinition>();
    
	

    private List<TerminalActionDefinition> Items.itemDetailesRecordsActions = new ArrayList<TerminalActionDefinition>();
    

    public List<ItemDetailesRecord> Items.getItemDetailesRecords(){
    	return this.itemDetailesRecords;
    }
    

    public List<TerminalActionDefinition> Items.getItemDetailesRecordsActions(){
    	return this.itemDetailesRecordsActions;
    }

    public String Items.getFocusField(){
    	return focusField;
    }
    public void Items.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
    public List<TerminalActionDefinition> Items.getActions(){
    	return actions;
    }
    
}
