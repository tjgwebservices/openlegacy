// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.as400.entities;

import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openlegacy.as400.entities.DisplayCommunicationsStatus.DisplayCommunicationsStatusRecord;

privileged @SuppressWarnings("unused") aspect DisplayCommunicationsStatusRecord_Aspect {

	
    
    private String DisplayCommunicationsStatusRecord.focusField;
	
    public String DisplayCommunicationsStatusRecord.getOpt(){
    	return this.opt;
    }
    
    public void DisplayCommunicationsStatusRecord.setOpt(String opt){
    	this.opt = opt;
    }

    public String DisplayCommunicationsStatusRecord.getIdentifier(){
    	return this.identifier;
    }
    

    public String DisplayCommunicationsStatusRecord.getMethod(){
    	return this.method;
    }
    


    /**
    	Focus on the given field, or on the first field in the table if none is given
    */
    public void DisplayCommunicationsStatusRecord.focus(String... field) {
        if (field.length > 0) {
            this.focusField = field[0];
        } else {
            this.focusField = "opt";
        }
    }

    public String DisplayCommunicationsStatusRecord.getFocus() {
        return focusField;
    }
    public void DisplayCommunicationsStatusRecord.setFocus(String focus) {
        this.focusField = focus;
    }
		
    public int DisplayCommunicationsStatusRecord.hashCode(){
		return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean DisplayCommunicationsStatusRecord.equals(Object other){
    	// TODO exclude terminal fields
		return EqualsBuilder.reflectionEquals(this,other);
    }
}