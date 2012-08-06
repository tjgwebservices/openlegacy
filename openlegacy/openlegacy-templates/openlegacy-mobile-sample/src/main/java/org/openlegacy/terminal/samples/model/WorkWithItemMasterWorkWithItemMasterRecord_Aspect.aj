// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.terminal.samples.model;

import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openlegacy.terminal.samples.model.WorkWithItemMaster.WorkWithItemMasterRecord;

privileged @SuppressWarnings("unused") aspect WorkWithItemMasterRecordTable_Aspect {
    
    public Integer WorkWithItemMasterRecord.getAction_(){
    	return this.action_;
    }
    
    public void WorkWithItemMasterRecord.setAction_(Integer action_){
    	this.action_ = action_;
    }

    public String WorkWithItemMasterRecord.getAlphaSearch(){
    	return this.alphaSearch;
    }
    

    public String WorkWithItemMasterRecord.getItemDescription(){
    	return this.itemDescription;
    }
    

    public Integer WorkWithItemMasterRecord.getItemNumber(){
    	return this.itemNumber;
    }
    


    public int WorkWithItemMasterRecord.hashCode(){
		return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean WorkWithItemMasterRecord.equals(Object other){
    	// TODO exclude terminal fields
		return EqualsBuilder.reflectionEquals(this,other);
    }
}
