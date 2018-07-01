package com.example.unit.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.mock.pojo.TenantConfig;
import com.example.test.framework.EntityExcelBuilder;
import com.example.test.framework.ExcelWriter;
import com.google.gson.Gson;

public class EntityExcelBuilderTest {
	
	
	private EntityExcelBuilder builder;
	private ExcelWriter writer;
	private static final String XL_DATA_FILE = "./src/test/resources/datafile_test.xlsx";
		
	@Before()
	public void init(){
		writer = new ExcelWriter(XL_DATA_FILE);
		try{
		System.out.println(new File("./").getAbsolutePath());
		builder = new EntityExcelBuilder(new Gson().
				fromJson(FileUtils.readFileToString(new File("./src/test/resources/tenant_config_test.json"),"UTF-8"),TenantConfig.class),writer);
		}catch(IOException e){
		  throw new IllegalArgumentException("could not read file",e);
		}
	}


	@Test
	public void readMandatoryFields(){
		
		Assert.assertTrue("verify number of mandatory field",builder.getAllMandatoryFields("customer").size() ==4);
	}
	
	@Test
	public void verifyOutputMessageFromFile(){
		
		Assert.assertTrue("verify error message",builder.getOutputMessages(XL_DATA_FILE,"customer").get(1).equals("error message"));
		Assert.assertTrue("verify success message",builder.getOutputMessages(XL_DATA_FILE,"customer").get(8).equals("success message"));
	}
}
