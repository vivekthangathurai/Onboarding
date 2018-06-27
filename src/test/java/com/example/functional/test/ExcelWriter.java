package com.example.functional.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
	
	private String path;
	private XSSFWorkbook workbook;
	private List<XSSFSheet> sheets;
	
	public ExcelWriter(String path){
		this.path = path;
		sheets = new ArrayList<XSSFSheet>();
	}
	
	public ExcelWriter createWorkbook(){
	 workbook = new XSSFWorkbook();
	 return this;
	}
	
	public XSSFSheet addSheet(String name){
		XSSFSheet sheet =null;
		if(sheets.stream().noneMatch(sh -> sh.getSheetName().equals(name))){
		 sheet = workbook.createSheet(name);
		sheets.add(sheet);
		}else{
			return getSheet(name);
		}
		return sheet;
	}
	
	public XSSFSheet getSheet(String name){
		
	     return sheets.stream()
				.filter(sheet -> sheet.getSheetName().equals(name))
				.findFirst().get();
				
	}
	
	public void write(){
		try (FileOutputStream out = new FileOutputStream(new File(path))){ // file name with path
	       workbook.write(out);
	       out.close();
		}catch(IOException fnf){
			//TODO log Exception
			fnf.printStackTrace();
		}
	}
	

}
