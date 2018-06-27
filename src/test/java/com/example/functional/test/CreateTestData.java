package com.example.functional.test;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.example.mock.pojo.TenantConfig;
import com.example.mock.service.CustomerOnboardingService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;

import net.minidev.json.JSONObject;

public class CreateTestData {
	
	public static void main(String[] args) throws Exception{
		
		
		
	WireMockServer wireMockServer = new WireMockServer();
		wireMockServer.start();
		CustomerOnboardingService service = new CustomerOnboardingService(wireMockServer);
		service.getTenantConfig("1234");
    CustomerOnboardingServiceClient client = new CustomerOnboardingServiceClient();
    
     HttpResponse response = client.getConfig("1234");
     String json = EntityUtils.toString(response.getEntity());
     
     Gson gson = new Gson();
     TenantConfig config = gson.fromJson(json,TenantConfig.class);
     ExcelWriter writer = new ExcelWriter("datafile.xlsx");
     
     EntityExcelBuilder builder = new EntityExcelBuilder(config, writer);
     
     builder.buildDataForMissingMandatoryFields("customer", false);
     builder.buildOnlyRequiredField("customer",false);

     builder.write();
	}


}
