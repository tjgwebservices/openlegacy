// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.rpc.samples.model;

import java.util.*;
import org.openlegacy.rpc.RpcEntity;

privileged @SuppressWarnings("unused") aspect Items_Aspect {
    declare parents: Items implements RpcEntity;
    
    public TopLevel Items.getTopLevel(){
    	return this.topLevel;
    }
    public void Items.setTopLevel(TopLevel topLevel){
    	this.topLevel = topLevel;
    }
    
}
