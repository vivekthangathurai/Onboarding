package com.example.mock.service;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

import java.io.File;

import com.example.utils.FileUtilities;
import com.github.tomakehurst.wiremock.WireMockServer;

public class CustomerOnboardingService {

	
	private WireMockServer wiremockserver;
	public CustomerOnboardingService(WireMockServer wiremock) {
		this.wiremockserver = wiremock;
		wiremockserver.start();
	}

	public void uploadSuccess(){
		stubFor(post("/customers/upload")
			    .willReturn(ok()));
	
	}
	
	public void addCustomer(){
				
		stubFor(post("/customers")
				.withRequestBody(matching("customer_success"))
			    .willReturn(okJson(FileUtilities.readFileAsString(new File("./src/main/resources/customer_success.json")))));	
	}
	
	public void getTenantConfig(String id){
		
		stubFor(get(String.format("/tenants/%s/config",id))
				.willReturn(okJson(FileUtilities.readFileAsString(new File("./src/main/resources/tenant_config.json")))));
		
	}

}
