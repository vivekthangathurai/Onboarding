package com.example.unit.tests;

import java.io.File;
import java.util.NoSuchElementException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.test.framework.ExcelWriter;

public class ExcelWriterTests {
	
private ExcelWriter writer;
private static final String FILENAME="test.xlsx";
	
@Before()
public void init(){
	
	writer = new ExcelWriter(FILENAME);
	writer.createWorkbook();
}


@Test
public void createExcelFile(){
	writer.write();
	Assert.assertTrue("excel file is created",new File(FILENAME).exists());
}


@Test
public void addSheet(){
	
	XSSFSheet s = writer.addSheet("test");
	Assert.assertTrue("created sheet should be present",s.getSheetName().equals("test"));
}

@Test(expected=NoSuchElementException.class)
public void getSheetNeagtive(){

	Assert.assertTrue("if sheet not found throw exception",writer.getSheet("test").getSheetName().equals("test"));
}

@Test()
public void getSheetPositive(){
	writer.addSheet("test");
	Assert.assertTrue("created sheet should be present",writer.getSheet("test").getSheetName().equals("test"));
}

}
