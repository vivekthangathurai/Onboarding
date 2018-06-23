package com.example.functional.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.example.mock.service.CustomerOnboardingService;

public class CustomerOnboardingServiceClient {
	
 private static final String BASE_URL ="http://localhost:8080/";	
 private HttpClient client;
 private CustomerOnboardingService service;
 
 public CustomerOnboardingServiceClient(){
	 client = HttpClientBuilder.create().build();
 }
 
 private HttpResponse execute(HttpUriRequest uri){
	 try {
	 return client.execute(uri);
	 }catch(Exception e){
		//TODO: Log exception
		 e.printStackTrace();
	 }
	return null;
 }
 
 public HttpResponse upload(){
	 
	 HttpPost post = new HttpPost(BASE_URL + "/customers/upload");
	 return execute(post);	 
 }
 
 
public HttpResponse addCustomer(){
	
	StringEntity entity = null;
	 HttpPost post = new HttpPost(BASE_URL + "/customers");
	 try{
		 entity = new StringEntity("customer_success");
	 }catch(IOException io){
		 //TODO: throw exception
		 io.printStackTrace();
	 }
	 
	 post.setEntity(entity);
	 return execute(post);	 
 }


public HttpResponse getConfig(String id){
	 HttpGet get = new HttpGet(String.format(BASE_URL + "/tenants/%s/config",id));	
	 return execute(get);	 
 }
 
 
 

}
