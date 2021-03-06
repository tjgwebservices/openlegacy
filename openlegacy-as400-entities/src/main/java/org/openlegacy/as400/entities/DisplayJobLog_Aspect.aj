// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.as400.entities;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.Color;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

privileged @SuppressWarnings("unused") aspect DisplayJobLog_Aspect {

    declare parents: DisplayJobLog implements ScreenEntity;
    private String DisplayJobLog.focusField;
    private List<TerminalActionDefinition> DisplayJobLog.actions = new ArrayList<TerminalActionDefinition>();
    
	

	

	

	

    

    public String DisplayJobLog.getJob(){
    	return this.job;
    }
    

    public String DisplayJobLog.getUser(){
    	return this.user;
    }
    

    public String DisplayJobLog.getNumber(){
    	return this.number;
    }
    

    public String DisplayJobLog.getJobLog(){
    	return this.jobLog;
    }
    


    public String DisplayJobLog.getFocusField(){
    	return focusField;
    }
    public void DisplayJobLog.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
    public List<TerminalActionDefinition> DisplayJobLog.getActions(){
    	return actions;
    }
    
}
