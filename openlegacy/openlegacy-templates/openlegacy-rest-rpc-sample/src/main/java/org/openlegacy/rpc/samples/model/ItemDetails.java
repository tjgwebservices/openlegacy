package org.openlegacy.rpc.samples.model;

import org.openlegacy.annotations.rpc.Action;
import org.openlegacy.annotations.rpc.RpcActions;
import org.openlegacy.annotations.rpc.RpcBooleanField;
import org.openlegacy.annotations.rpc.RpcDateField;
import org.openlegacy.annotations.rpc.RpcEntity;
import org.openlegacy.annotations.rpc.RpcField;
import org.openlegacy.annotations.rpc.RpcNumericField;
import org.openlegacy.annotations.rpc.RpcPart;
import org.openlegacy.rpc.actions.RpcActions.READ;
import org.openlegacy.rpc.actions.RpcActions.UPDATE;

import java.util.Date;

@SuppressWarnings("unused")
@RpcEntity(name = "ItemDetails")
@RpcActions(actions = { @Action(action = READ.class, path = "/QSYS.LIB/RMR2L1.LIB/FULLDETAIL.PGM", global = false),
		@Action(action = UPDATE.class, path = "/QSYS.LIB/RMR2L1.LIB/UPDATEITEM.PGM") })
public class ItemDetails implements org.openlegacy.rpc.RpcEntity {

	@RpcNumericField(minimumValue = -99999999, maximumValue = 99999999, decimalPlaces = 0)
	@RpcField(length = 8, key = true, originalName = "ITEM-NUM", displayName = "Item Number")
	private Integer itemNum;

	private ItemRecord itemRecord;
	private Shipping shipping;

	@RpcPart(name = "ItemRecord")
	public static class ItemRecord {

		@RpcField(length = 16, originalName = "ITEM-NAME")
		private String itemName;

		@RpcField(length = 28, originalName = "DESCRIPTION")
		private String description;

		@RpcNumericField(minimumValue = -9999, maximumValue = 9999, decimalPlaces = 0)
		@RpcField(length = 4, originalName = "WEIGHT")
		private Integer weight;

		@RpcDateField(pattern = "MM/dd/yyyy")
		@RpcField(length = 10, originalName = "CREATED-DATE")
		private Date created_date;

		@RpcBooleanField(falseValue = "false", trueValue = "true")
		@RpcField(originalName = "AVAILABILITY", length = 5)
		private Boolean availability;
	}

	@RpcPart(name = "Shipping")
	public static class Shipping {

		@RpcField(length = 10, originalName = "SHIPPING-METHOD")
		private String shippingMethod;

		@RpcNumericField(minimumValue = -9999, maximumValue = 9999, decimalPlaces = 0)
		@RpcField(length = 4, originalName = "DAYS")
		private Integer days;

	}
}
