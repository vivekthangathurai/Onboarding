package com.example.mock.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entity {

@SerializedName("entity")
@Expose
private String entity;

@SerializedName("parent")
@Expose
private String parent;

@SerializedName("fields")
@Expose
private List<Field> fields = null;

public String getEntity() {
return entity;
}

public void setEntity(String entity) {
this.entity = entity;
}

public List<Field> getFields() {
return fields;
}

public void setFields(List<Field> fields) {
this.fields = fields;
}

public String getParent() {
	return parent;
}

public void setParent(String parent) {
	this.parent = parent;
}


}