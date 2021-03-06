package com.example.test.framework;

import java.util.ArrayList;
import java.util.List;

public class DynamicField {
	
	private String entity;
	private String fieldname;
	private Boolean required;
	private String type;
	private Boolean autogenerated =false;
	private boolean isSet = true;
	private static long autoGeneratedValue =0;
	private boolean hasParent;
	private boolean stopincrement = false;
	private List<Validation> validations;
	
	public List<Validation> getValidations() {
		if(validations == null){
			validations = new ArrayList<Validation>();
		}
		return validations;
	}
	public void setValidations(List<Validation> validations) {
		this.validations = validations;
	}
	public enum Validation {
		LENGTH,NUMERIC,ALPHA_NUMERIC;
		int length;

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}
		
	}
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getAutogenerated() {
		return autogenerated;
	}
	public void setAutogenerated(Boolean autogenerated) {
		this.autogenerated = autogenerated;
	}
	public boolean isSet() {
		return isSet;
	}
	public void setSet(boolean isSet) {
		this.isSet = isSet;
	}
	public long getAutoGeneratedValue() {
		return autoGeneratedValue;
	}
	public long incrementAutoGeneratedValue() {
		if(!stopincrement)
		  return ++autoGeneratedValue;
		else
		  return autoGeneratedValue;  	
	}
	public boolean isHasParent() {
		return hasParent;
	}
	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}
	public boolean isStopincrement() {
		return stopincrement;
	}
	public void setStopincrement(boolean stopincrement) {
		this.stopincrement = stopincrement;
	}
	
	
	
	

}
