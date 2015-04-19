// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.as400.entities;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.Color;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

privileged @SuppressWarnings("unused") aspect DisplayJobRunAttributes_Aspect {

    declare parents: DisplayJobRunAttributes implements ScreenEntity;
    private String DisplayJobRunAttributes.focusField;
    private List<TerminalActionDefinition> DisplayJobRunAttributes.actions = new ArrayList<TerminalActionDefinition>();
    
	

	

	

	

	

	

	

	

	

	

	

	

	

	

	

	

    

    public String DisplayJobRunAttributes.getJob(){
    	return this.job;
    }
    

    public String DisplayJobRunAttributes.getUser(){
    	return this.user;
    }
    

    public String DisplayJobRunAttributes.getNumber(){
    	return this.number;
    }
    

    public String DisplayJobRunAttributes.getRunPriority(){
    	return this.runPriority;
    }
    

    public String DisplayJobRunAttributes.getTimeSliceInMilliseconds(){
    	return this.timeSliceInMilliseconds;
    }
    

    public String DisplayJobRunAttributes.getEligiableForPurge(){
    	return this.eligiableForPurge;
    }
    

    public String DisplayJobRunAttributes.getDefaultWaitTimeInSeconds(){
    	return this.defaultWaitTimeInSeconds;
    }
    

    public String DisplayJobRunAttributes.getMaximumCpuTimeInMilliseconds(){
    	return this.maximumCpuTimeInMilliseconds;
    }
    

    public String DisplayJobRunAttributes.getCpuTimeUsed(){
    	return this.cpuTimeUsed;
    }
    

    public String DisplayJobRunAttributes.getMaximumTemporaryStorageInMegabytes(){
    	return this.maximumTemporaryStorageInMegabytes;
    }
    

    public String DisplayJobRunAttributes.getTemporaryStorageUsed(){
    	return this.temporaryStorageUsed;
    }
    

    public String DisplayJobRunAttributes.getMaximumThreads(){
    	return this.maximumThreads;
    }
    

    public Integer DisplayJobRunAttributes.getThreads(){
    	return this.threads;
    }
    

    public String DisplayJobRunAttributes.getGroup(){
    	return this.group;
    }
    

    public String DisplayJobRunAttributes.getLevel(){
    	return this.level;
    }
    

    public String DisplayJobRunAttributes.getResourcesAffinityGroup(){
    	return this.resourcesAffinityGroup;
    }
    


    public String DisplayJobRunAttributes.getFocusField(){
    	return focusField;
    }
    public void DisplayJobRunAttributes.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
    public List<TerminalActionDefinition> DisplayJobRunAttributes.getActions(){
    	return actions;
    }
    
}