// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.openlegacyrestsample.openlegacy;

import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.openlegacyrestsample.openlegacy.Warehouses.WarehousesRecord;

privileged @SuppressWarnings("unused") aspect WarehousesRecord_Aspect {

	
    
    private String WarehousesRecord.focusField;
	
    public String WarehousesRecord.getAct(){
    	return this.act;
    }
    
    public void WarehousesRecord.setAct(String act){
    	this.act = act;
    }

    public String WarehousesRecord.getSerialNumber(){
    	return this.serialNumber;
    }
    

    public String WarehousesRecord.getDescription(){
    	return this.description;
    }
    


    /**
    	Focus on the given field, or on the first field in the table if none is given
    */
    public void WarehousesRecord.focus(String... field) {
        if (field.length > 0) {
            this.focusField = field[0];
        } else {
            this.focusField = "act";
        }
    }

    public String WarehousesRecord.getFocus() {
        return focusField;
    }
    public void WarehousesRecord.setFocus(String focus) {
        this.focusField = focus;
    }
		
    public int WarehousesRecord.hashCode(){
		return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean WarehousesRecord.equals(Object other){
    	// TODO exclude terminal fields
		return EqualsBuilder.reflectionEquals(this,other);
    }
}