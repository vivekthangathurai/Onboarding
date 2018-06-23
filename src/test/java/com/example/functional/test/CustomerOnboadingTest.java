package com.example.functional.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.example.mock.service.CustomerOnboardingService;
import com.github.tomakehurst.wiremock.WireMockServer;

public class CustomerOnboadingTest {
	
	
	private CustomerOnboardingService service;
	private CustomerOnboardingServiceClient client;
	@BeforeTest()
	public void createMock(){
		WireMockServer wireMockServer = new WireMockServer();
		wireMockServer.start();
		service = new CustomerOnboardingService(wireMockServer);
		service.uploadSuccess();
		service.addCustomer();
		service.getTenantConfig("1234");
		client = new CustomerOnboardingServiceClient();
	}
	
	
	@Test
	public void testUpload(){
		HttpResponse response = client.upload();
		System.out.println(response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void addCustomer() throws IOException{
		HttpResponse response = client.addCustomer();
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(EntityUtils.toString(response.getEntity()));
	}
	
	
	

}
