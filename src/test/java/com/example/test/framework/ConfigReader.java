package com.example.test.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.mock.pojo.Entity;
import com.example.mock.pojo.Field;
import com.example.mock.pojo.TenantConfig;
import com.example.test.framework.DynamicField.Validation;

public class ConfigReader {
	
	
	private TenantConfig config;
	public ConfigReader(TenantConfig config) {
		this.config = config;		
	}
	
	public List<Entity> getEntities(){
		
		return config.getEntities();
		
	}
	
	public Entity getEntityByName(String name){
		
		return getEntities().stream().filter(f -> f.getEntity().equals(name)).findFirst().orElseThrow(() -> new IllegalArgumentException("Entity not found with name: " + name));
	}
	
	public HashMap<String,List<DynamicField>> mapEntityToFields(){
	  HashMap<String,List<DynamicField>> fields = new HashMap<>();
		for(Entity entity : config.getEntities()){
			List<DynamicField> dfs = new ArrayList<DynamicField>();
			for(Field f : entity.getFields()){
				DynamicField df = new DynamicField();
				df.setEntity(entity.getEntity());
				df.setFieldname(f.getName());
				df.setRequired(f.getRequired());
				df.setType(f.getType());
				df.setAutogenerated(f.getAutogenerated());
				if(f.getValidations() != null){
					if (f.getValidations().getLength() > 0){
						Validation val = Validation.LENGTH;
						val.setLength(f.getValidations().getLength());
						df.getValidations().add(val);
						
					}
				}
				
				dfs.add(df);
				
			}
			fields.put(entity.getEntity(),dfs);
		}
		
		return fields;
	}
	

}
