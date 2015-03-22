package raz.test.file.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class StringUtil {
	
	//static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);
	
	public static String readFile(File aFile) throws IOException {
		StringBuilder contents = new StringBuilder();
		BufferedReader input = null; 
		try{
			input = new BufferedReader(new FileReader(aFile));
			String line = null;
			while((line = input.readLine())!=null){
				contents.append(line);
		        contents.append(System.getProperty("line.separator"));
			}
		} finally{
			if(input!=null){
				try{
					input.close();
				}catch(IOException ioe){
					//Do not throw Exception and stop the Normal Course of the program,
					//because this is not so critical, but in time..
					//LOG.warn("Error Closing the Resource!",ioe);
				}
			}			
		}
		return contents.toString();
	}

}
