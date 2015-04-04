// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.remoting.entities;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.Color;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

privileged @SuppressWarnings("unused") aspect SignOn_Aspect {

    declare parents: SignOn implements ScreenEntity;
    private String SignOn.focusField;
    private List<TerminalActionDefinition> SignOn.actions = new ArrayList<TerminalActionDefinition>();
    
	

	

	

	

    

    public String SignOn.getUser(){
    	return this.user;
    }
    
    public void SignOn.setUser(String user){
    	this.user = user;
    }

    public String SignOn.getPassword(){
    	return this.password;
    }
    
    public void SignOn.setPassword(String password){
    	this.password = password;
    }

    public String SignOn.getErrorMessage(){
    	return this.errorMessage;
    }
    

    public String SignOn.getCurrentLibary(){
    	return this.currentLibary;
    }
    
    public void SignOn.setCurrentLibary(String currentLibary){
    	this.currentLibary = currentLibary;
    }


    public String SignOn.getFocusField(){
    	return focusField;
    }
    public void SignOn.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
    public List<TerminalActionDefinition> SignOn.getActions(){
    	return actions;
    }
    
}
