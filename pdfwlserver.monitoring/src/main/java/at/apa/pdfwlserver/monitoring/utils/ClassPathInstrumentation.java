package at.apa.pdfwlserver.monitoring.utils;

import java.io.IOException;

/**
 * http://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java
 * http://stackoverflow.com/questions/778187/getting-directory-path-to-class-file-containing-main
 * */

public class ClassPathInstrumentation {

	
	/**
	 * Pentru o cale relativa, si nu absoluta
	 * -Dlog4j.configuration=file:log4j.properties
	 * => asta se traduce fisierul "log4j.properties" este in workingDir
	 * */
	public static void printWorkingDir() {
		String current = " n/a";
		try {
			current = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("Current dir:" + current);
		String currentDir = System.getProperty("user.dir");
		System.out.println("Current dir using System:" + currentDir);
	}

}
