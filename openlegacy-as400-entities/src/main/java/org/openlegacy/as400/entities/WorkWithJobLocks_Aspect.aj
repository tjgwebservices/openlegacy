// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.as400.entities;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.Color;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

privileged @SuppressWarnings("unused") aspect WorkWithJobLocks_Aspect {

    declare parents: WorkWithJobLocks implements ScreenEntity;
    private String WorkWithJobLocks.focusField;
    private List<TerminalActionDefinition> WorkWithJobLocks.actions = new ArrayList<TerminalActionDefinition>();
    
	

	

	

	

	

    private List<TerminalActionDefinition> WorkWithJobLocks.workWithJobLocksRecordsActions = new ArrayList<TerminalActionDefinition>();
    

    public String WorkWithJobLocks.getJob(){
    	return this.job;
    }
    

    public String WorkWithJobLocks.getUser(){
    	return this.user;
    }
    

    public String WorkWithJobLocks.getNumber(){
    	return this.number;
    }
    

    public String WorkWithJobLocks.getJobStatus(){
    	return this.jobStatus;
    }
    

    public List<WorkWithJobLocksRecord> WorkWithJobLocks.getWorkWithJobLocksRecords(){
    	return this.workWithJobLocksRecords;
    }
    

    public List<TerminalActionDefinition> WorkWithJobLocks.getWorkWithJobLocksRecordsActions(){
    	return this.workWithJobLocksRecordsActions;
    }

    public String WorkWithJobLocks.getFocusField(){
    	return focusField;
    }
    public void WorkWithJobLocks.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
    public List<TerminalActionDefinition> WorkWithJobLocks.getActions(){
    	return actions;
    }
    
}