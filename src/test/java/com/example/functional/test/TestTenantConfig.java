package com.example.functional.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.example.mock.pojo.TenantConfig;
import com.example.mock.service.CustomerOnboardingService;
import com.example.test.framework.CustomerOnboardingServiceClient;
import com.example.test.framework.EntityExcelBuilder;
import com.example.test.framework.ExcelWriter;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;

public class TestTenantConfig {

	private WireMockServer wireMockServer;
	private CustomerOnboardingService service;
	private CustomerOnboardingServiceClient client;
	TestTenantConfig(){
		wireMockServer = new WireMockServer();
		wireMockServer.start();
		service = new CustomerOnboardingService(wireMockServer);
		client = new CustomerOnboardingServiceClient();
	}
	
	public void createTestData() throws Exception{

	 service.getTenantConfig("1234");
     HttpResponse response = client.getConfig("1234");
     String json = EntityUtils.toString(response.getEntity());
     
     Gson gson = new Gson();
     TenantConfig config = gson.fromJson(json,TenantConfig.class);
     ExcelWriter writer = new ExcelWriter("datafile_input.xlsx");
     
     EntityExcelBuilder builder = new EntityExcelBuilder(config, writer);
     
     builder.buildDataForMissingMandatoryFields("customer", false);
     builder.buildOnlyRequiredField("customer",false);
     getUploadResult("datafile_output.xlsx");
     builder.write();
     Map<Integer,String> expected = builder.getOutputMessages("datafile_input.xlsx","customer");
     Map<Integer,String> actual = builder.getOutputMessages("datafile_output.xlsx","customer");
     
     compareResults(expected, actual);
     
	}
	
	public void compareResults(Map<Integer,String> expected, Map<Integer,String> actual){
		
		expected.keySet().stream().forEach(key -> {
			String exp  =expected.get(key);
			String act = actual.get(key);
			if(exp == null || act ==null){
				System.out.println("Record with Customer Id " + key + "has null value");	
			}
			if(!exp.equals(act)){
				System.out.println("Record with Customer Id " + key + "test results did not match");
			}else{
				System.out.println("Record with Customer Id " + key + " test results match");
			}
		});
	}
	
	public void getUploadResult(String filePath) throws IOException{
		service.uploadSuccess();
		HttpResponse response = client.upload();
		InputStream is = response.getEntity().getContent();
		
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		int inByte;
		while((inByte = is.read()) != -1)
		     fos.write(inByte);
		is.close();
		fos.close();
	}
	
	
	
	public static void main(String[] args) throws Exception{
		TestTenantConfig testData = new TestTenantConfig();
		testData.createTestData();
	}


}