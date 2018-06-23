package com.example.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtilities {
	
	public static String readFileAsString(File fileToRead){
		String fileAsString = null;
		try{
			fileAsString = FileUtils.readFileToString(fileToRead, "UTF-8");
		}catch(IOException e){
			//TODO Log exception
			e.printStackTrace();
		}
		return fileAsString;
	}

}
