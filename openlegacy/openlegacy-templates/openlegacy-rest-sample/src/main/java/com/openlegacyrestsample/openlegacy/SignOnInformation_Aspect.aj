// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.openlegacyrestsample.openlegacy;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.Color;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

privileged @SuppressWarnings("unused") aspect SignOnInformation_Aspect {

    declare parents: SignOnInformation implements ScreenEntity;
    private String SignOnInformation.focusField;
    private List<TerminalActionDefinition> SignOnInformation.actions = new ArrayList<TerminalActionDefinition>();
    
	

    

    public String SignOnInformation.getPreviousSignOn(){
    	return this.previousSignOn;
    }
    


    public String SignOnInformation.getFocusField(){
    	return focusField;
    }
    public void SignOnInformation.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
    public List<TerminalActionDefinition> SignOnInformation.getActions(){
    	return actions;
    }
    
}
