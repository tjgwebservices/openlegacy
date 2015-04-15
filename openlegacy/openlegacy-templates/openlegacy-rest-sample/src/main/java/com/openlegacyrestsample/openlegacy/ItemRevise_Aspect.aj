// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.openlegacyrestsample.openlegacy;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.Color;
import org.openlegacy.terminal.definitions.TerminalActionDefinition;

privileged @SuppressWarnings("unused") aspect ItemRevise_Aspect {

    declare parents: ItemRevise implements ScreenEntity;
    private String ItemRevise.focusField;
    private List<TerminalActionDefinition> ItemRevise.actions = new ArrayList<TerminalActionDefinition>();
    
	

	

	

	

	

	

	

	

	

	

	

	

	

	

	

	

	

	

	

    

    public String ItemRevise.getItemNumber(){
    	return this.itemNumber;
    }
    

    public String ItemRevise.getItemDescription(){
    	return this.itemDescription;
    }
    
    public void ItemRevise.setItemDescription(String itemDescription){
    	this.itemDescription = itemDescription;
    }

    public String ItemRevise.getAlphaSearch(){
    	return this.alphaSearch;
    }
    
    public void ItemRevise.setAlphaSearch(String alphaSearch){
    	this.alphaSearch = alphaSearch;
    }

    public String ItemRevise.getSupercedingItemto(){
    	return this.supercedingItemto;
    }
    
    public void ItemRevise.setSupercedingItemto(String supercedingItemto){
    	this.supercedingItemto = supercedingItemto;
    }

    public String ItemRevise.getSupercedingItemfrom(){
    	return this.supercedingItemfrom;
    }
    
    public void ItemRevise.setSupercedingItemfrom(String supercedingItemfrom){
    	this.supercedingItemfrom = supercedingItemfrom;
    }

    public String ItemRevise.getSubstituteItemNumber(){
    	return this.substituteItemNumber;
    }
    
    public void ItemRevise.setSubstituteItemNumber(String substituteItemNumber){
    	this.substituteItemNumber = substituteItemNumber;
    }

    public String ItemRevise.getManufacturersItemNo(){
    	return this.manufacturersItemNo;
    }
    
    public void ItemRevise.setManufacturersItemNo(String manufacturersItemNo){
    	this.manufacturersItemNo = manufacturersItemNo;
    }

    public String ItemRevise.getItemWeight(){
    	return this.itemWeight;
    }
    
    public void ItemRevise.setItemWeight(String itemWeight){
    	this.itemWeight = itemWeight;
    }

    public String ItemRevise.getItemClass(){
    	return this.itemClass;
    }
    
    public void ItemRevise.setItemClass(String itemClass){
    	this.itemClass = itemClass;
    }

    public String ItemRevise.getItemClassName(){
    	return this.itemClassName;
    }
    

    public String ItemRevise.getStockGroup(){
    	return this.stockGroup;
    }
    
    public void ItemRevise.setStockGroup(String stockGroup){
    	this.stockGroup = stockGroup;
    }

    public String ItemRevise.getStockGroupName(){
    	return this.stockGroupName;
    }
    

    public String ItemRevise.getUnitOfMeasure(){
    	return this.unitOfMeasure;
    }
    
    public void ItemRevise.setUnitOfMeasure(String unitOfMeasure){
    	this.unitOfMeasure = unitOfMeasure;
    }

    public Integer ItemRevise.getPackingMultiplier(){
    	return this.packingMultiplier;
    }
    
    public void ItemRevise.setPackingMultiplier(Integer packingMultiplier){
    	this.packingMultiplier = packingMultiplier;
    }

    public String ItemRevise.getOuterUnitOfMeasure(){
    	return this.outerUnitOfMeasure;
    }
    
    public void ItemRevise.setOuterUnitOfMeasure(String outerUnitOfMeasure){
    	this.outerUnitOfMeasure = outerUnitOfMeasure;
    }

    public Integer ItemRevise.getOuterQuantity(){
    	return this.outerQuantity;
    }
    
    public void ItemRevise.setOuterQuantity(Integer outerQuantity){
    	this.outerQuantity = outerQuantity;
    }

    public String ItemRevise.getPalletLabelRequired(){
    	return this.palletLabelRequired;
    }
    
    public void ItemRevise.setPalletLabelRequired(String palletLabelRequired){
    	this.palletLabelRequired = palletLabelRequired;
    }

    public String ItemRevise.getVatCode(){
    	return this.vatCode;
    }
    
    public void ItemRevise.setVatCode(String vatCode){
    	this.vatCode = vatCode;
    }

    public String ItemRevise.getFocusField(){
    	return focusField;
    }
    public void ItemRevise.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
    public List<TerminalActionDefinition> ItemRevise.getActions(){
    	return actions;
    }
    
}
