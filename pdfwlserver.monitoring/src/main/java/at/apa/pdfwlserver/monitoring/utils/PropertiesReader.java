package at.apa.pdfwlserver.monitoring.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	
	public static String KEY_freemarker 			= "freemarker.template.filepath";
	public static String KEY_status_page 			= "status.page.html";
	public static String KEY_regular_checks_repeat 	= "regular.cheks.repeat.period";
	public static String KEY_file_extension 		= "checked.file.extension";
	public static String KEY_webservice_url 		= "Services2Impl.webservice.url";
	
	private String freemarkerTemplateFilePath;
	private String checkedFileExtension;
	private int regularCheckRepeatPeriod;
	private String webServiceUrl;
	
	private static PropertiesReader _instance;
	private static File propsFilePath;
	
	/**
	 * (instantiates) reInstantiates the properties with the given filePath
	 * @throws IOException 
	 * */
	public static PropertiesReader getInstance(File filePath) throws IOException{
		_instance = new PropertiesReader(filePath);
		return _instance;
	}
	
	private PropertiesReader(){}
	
	private PropertiesReader(File filePath) throws IOException{
		propsFilePath = filePath;
		readProperties(propsFilePath);
	}
	
	/**
	 * can not return null, either reads props, or throws IOException
	 * @throws IOException 
	 * */
	private static Properties readProperties(File filePath) throws IOException{
		Properties props = new Properties();
		InputStream fis = null;
		try{
			fis = new FileInputStream(filePath); 
			props.load(fis);
		} finally{
			if(null!= fis){
				try{fis.close();} catch (IOException ignored){}
			}
		}
		
		return props;
		
	}
	
	/**
	 * Attempts to silently read the properties file
	 * each time the method is called, the value is freshly read from properties file.
	 * in case we have a IOException we try to return the previous value. 
	 * (if it was not null. it should not be null because the a 1st value is safely initialized on <code>getInstance()</code> )
	 * 	
	 * */
	public String getFreemarkerTemplateFilePath(){
		Properties prop = null; 
		try {
			prop = readProperties(propsFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freemarkerTemplateFilePath;
		
	}
	
	public String getCheckedFileExtension(){
		
		return checkedFileExtension;
	}
	
	public int getRegularCheckRepeatPeriod(){
		
		return regularCheckRepeatPeriod;
	}

	public String getWebServiceUrl(){
		return webServiceUrl;
	}
	
}
