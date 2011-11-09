// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package apps.inventory.screens;

import org.openlegacy.terminal.ScreenEntity;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

privileged aspect ItemsList_Aspect {
    
    declare @type: ItemsList : @Component;
	declare @type: ItemsList : @Scope("prototype");
    

    declare parents: ItemsList implements ScreenEntity;
    private String ItemsList.focusField;
    
	
	
    

    

    public String ItemsList.getPositionTo(){
    	return this.positionTo;
    }
    


    public String ItemsList.getFocusField(){
    	return focusField;
    }
    public void ItemsList.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
}
