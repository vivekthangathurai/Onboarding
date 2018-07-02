package com.example.functional.test;

public class TestScalability {
	
	/**
	 * Test for 1000 customer data, Single tenant.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
	
		TestTenantConfig testData = new TestTenantConfig();
		testData.createDataPerformanceAndValidate();
		
		//creates data for 5000 tenants and 50000 customer each.
		testData.createPerformanceData();
	}


}
