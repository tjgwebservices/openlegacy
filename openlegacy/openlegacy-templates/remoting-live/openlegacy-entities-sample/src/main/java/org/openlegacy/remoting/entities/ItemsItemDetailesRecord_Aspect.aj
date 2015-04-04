// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.remoting.entities;

import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openlegacy.remoting.entities.Items.ItemDetailesRecord;

privileged @SuppressWarnings("unused") aspect ItemDetailesRecord_Aspect {

	
    
    private String ItemDetailesRecord.focusField;
	
    public String ItemDetailesRecord.getAct(){
    	return this.act;
    }
    
    public void ItemDetailesRecord.setAct(String act){
    	this.act = act;
    }

    public String ItemDetailesRecord.getAlphaSearch(){
    	return this.alphaSearch;
    }
    

    public String ItemDetailesRecord.getItemDescription(){
    	return this.itemDescription;
    }
    

    public String ItemDetailesRecord.getItemNumber(){
    	return this.itemNumber;
    }
    


    /**
    	Focus on the given field, or on the first field in the table if none is given
    */
    public void ItemDetailesRecord.focus(String... field) {
        if (field.length > 0) {
            this.focusField = field[0];
        } else {
            this.focusField = "act";
        }
    }

    public String ItemDetailesRecord.getFocus() {
        return focusField;
    }
    public void ItemDetailesRecord.setFocus(String focus) {
        this.focusField = focus;
    }
		
    public int ItemDetailesRecord.hashCode(){
		return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean ItemDetailesRecord.equals(Object other){
    	// TODO exclude terminal fields
		return EqualsBuilder.reflectionEquals(this,other);
    }
}