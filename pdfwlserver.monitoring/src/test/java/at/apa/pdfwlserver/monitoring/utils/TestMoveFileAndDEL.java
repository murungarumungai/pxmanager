package at.apa.pdfwlserver.monitoring.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestMoveFileAndDEL {
	
	/**
	 * Try to move one file from incoming to success to see if the date 
	 * is modified
	 * this mimics the importer.
	 * This test purpose is to see if importer changes the last modified date of a file 
	 * */
	public static void main(String[] args){
		
		setModifiedDateOnFile();
		
		/*
		String derStandardBaseDirPath = "CheckJobTest/derstandard";
		File derStandardBaseDir = new File(derStandardBaseDirPath);
		
		File successDir = new File(derStandardBaseDir, "success");
		
		File importDir = new File(derStandardBaseDir, "import");
		
		File zipFile = new File(importDir, "20130204185333_stdIPAD_20130205_abend.zip" );
		
		try {
			IOUtils.safeMoveFile( zipFile, successDir );
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		*/
		
	}
	
	public static void setModifiedDateOnFile(){
		String derStandardBaseDirPath = "CheckJobTest/derstandard";
		File derStandardBaseDir = new File(derStandardBaseDirPath);
		File successDir = new File(derStandardBaseDir, "success");
		File zipFile = new File(successDir, "20130204185333_stdIPAD_20130205_abend.zip" );
		
		Date now = DateUtils.parseDateTime("11.03.2013 14:55");
		
		zipFile.setLastModified(now.getTime());
	}

}
