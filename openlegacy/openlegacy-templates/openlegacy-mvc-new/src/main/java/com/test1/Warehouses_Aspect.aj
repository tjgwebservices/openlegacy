// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.test1;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.Color;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

privileged @SuppressWarnings("unused") aspect Warehouses_Aspect {

    declare parents: Warehouses implements ScreenEntity;
    private String Warehouses.focusField;
    private List<TerminalActionDefinition> Warehouses.actions = new ArrayList<TerminalActionDefinition>();
    
	

    

    public List<WarehousesRecord> Warehouses.getWarehousesRecords(){
    	return this.warehousesRecords;
    }
    




    public String Warehouses.getFocusField(){
    	return focusField;
    }
    public void Warehouses.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
    public List<TerminalActionDefinition> Warehouses.getActions(){
    	return actions;
    }
    
}