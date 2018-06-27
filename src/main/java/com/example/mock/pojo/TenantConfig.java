package com.example.mock.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TenantConfig {

@SerializedName("entities")
@Expose
private List<Entity> entities = null;

public List<Entity> getEntities() {
return entities;
}

public void setEntities(List<Entity> entities) {
this.entities = entities;
}

}