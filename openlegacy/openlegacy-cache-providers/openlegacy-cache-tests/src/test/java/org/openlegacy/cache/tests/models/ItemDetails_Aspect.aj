// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.cache.tests.models;

import org.openlegacy.cache.tests.models.ItemDetails.ItemRecord;
import org.openlegacy.cache.tests.models.ItemDetails.Shipping;
import org.openlegacy.definitions.RpcActionDefinition;
import org.openlegacy.rpc.RpcEntity;

import java.util.ArrayList;
import java.util.List;

privileged @SuppressWarnings("unused") aspect ItemDetails_Aspect {
	declare parents: ItemDetails implements RpcEntity;

	public List<RpcActionDefinition> ItemDetails.getActions() {
		return new ArrayList<RpcActionDefinition>();
	}

	public Integer ItemDetails.getItemNum() {
		return this.itemNum;
	}
	public void ItemDetails.setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}
	public ItemRecord ItemDetails.getItemRecord() {
		return this.itemRecord;
	}
	public void ItemDetails.setItemRecord(ItemRecord itemRecord) {
		this.itemRecord = itemRecord;
	}
	public Shipping ItemDetails.getShipping() {
		return this.shipping;
	}
	public void ItemDetails.setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
}
