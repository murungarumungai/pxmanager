package at.apa.pdfwlserver.monitoring.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class FileScanner {

	public static boolean parseErrorDescriptionFile(File errorDescriptionFile,
			List<String> errorStrings) {
		boolean result = false;
		Scanner s = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					errorDescriptionFile));
			s = new Scanner(br);
			s.useDelimiter("\\Z");// read whole file

			String content = s.next();
			for(String errorString:errorStrings){
				if(content.contains(errorString)){
					result = true;
					break;
				}
			}
		} catch (FileNotFoundException fnf) {
			//file existence should be check by caller
			fnf.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}

		return result;
	}

}
