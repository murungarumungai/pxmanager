package at.apa.pdfwlserver.monitoring.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesReader {

	private static Logger logger = LoggerFactory
			.getLogger(PropertiesReader.class);

	public static final String KEY_freemarker 				= "freemarker.template.filepath";
	public static final String KEY_status_page 				= "status.page.html";
	public static final String KEY_regular_checks_repeat 	= "regular.cheks.repeat.period";
	public static final String KEY_file_extension 			= "checked.file.extension";
	public static final String KEY_webservice_url 			= "Services2Impl.webservice.url";
	public static final String KEY_error_file_extension 	= "error.description.file.extension";
	public static final String KEY_webservice_name 			= "Services2Impl.webservice.name";
	public static final String KEY_webservice_namespace 	= "Services2Impl.webservice.namespace";
	
	private String freemarkerTemplateFilePath;
	private String checkedFileExtension;
	private int regularCheckRepeatPeriod;
	private String webServiceUrl;
	private String webServiceName;
	private String webServiceNamespace;
	private String statusPageFilePath;
	private String errorFileExtension;

	private String paramAppKey;
	private String paramNetworkType;
	private String paramRegion;
	private String paramUdid;
	private String paramDeviceType;
	private String paramDeviceLocale;
	private String paramClientVersion;
	
	private static PropertiesReader _instance;
	private static File propsFilePath;

	/**
	 * (instantiates) reInstantiates the properties with the given filePath
	 * 
	 * @throws IOException
	 * */
	public static PropertiesReader getInstance(File filePath)
			throws IOException {
		_instance = new PropertiesReader(filePath);
		return _instance;
	}

	private PropertiesReader() {
	}

	private PropertiesReader(File filePath) throws IOException {
		propsFilePath = filePath;
		readProperties(propsFilePath);
		//init
		getFreemarkerTemplateFilePath();
		getRegularCheckRepeatPeriod();
		getCheckedFileExtension();
		getWebServiceUrl();
		getErrorFileExtension();
	}

	/**
	 * can not return null, either reads props, or throws IOException
	 * 
	 * @throws IOException
	 * */
	private static Properties readProperties(File filePath) throws IOException {
		Properties props = new Properties();
		InputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			props.load(fis);
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException ignored) {
				}
			}
		}
		return props;
	}

	private static String readProperty(String key) {
		String result = null;
		Properties prop = null;
		try {
			prop = readProperties(propsFilePath);
		} catch (IOException e) {
			logger.warn("Failed to read from properties file", e);
		}

		if (null != prop) {
			result = prop.getProperty(key);
		}
		return result;
	}

	/**
	 * Attempts to silently read the properties file each time the method is
	 * called, the value is freshly read from properties file. in case we have a
	 * IOException we try to return the previous value. (if it was not null. it
	 * should not be null because at least the 1st value is safely initialized
	 * on <code>getInstance()</code> )
	 * 
	 * */
	public String getFreemarkerTemplateFilePath() {
		String newValue = readProperty(KEY_freemarker);
		if (null != newValue) {
			freemarkerTemplateFilePath = newValue;
		}
		return freemarkerTemplateFilePath;

	}

	public String getCheckedFileExtension() {
		String newValue = readProperty(KEY_file_extension);
		if (null != newValue) {
			checkedFileExtension = newValue;
		}
		return checkedFileExtension;
	}

	public int getRegularCheckRepeatPeriod() {
		int val = -1;
		String newValue = readProperty(KEY_regular_checks_repeat);
		if (null != newValue) {
			try {
				val = Integer.parseInt(newValue);
			} catch (NumberFormatException nfe) {/*ignored*/}
			if(val!=-1){
				regularCheckRepeatPeriod = val;
			}
		}
		return regularCheckRepeatPeriod;
	}

	public String getWebServiceUrl() {
		String newVal = readProperty(KEY_webservice_url);
		if(null!=newVal){
			webServiceUrl = newVal;
		}
		return webServiceUrl;
	}
	
	public String getStatusPageFilePath(){
		String newVal = readProperty(KEY_status_page);
		if(null!=newVal){
			statusPageFilePath = newVal;
		}
		return statusPageFilePath;
	}

	/**
	 * It always reads the latest value from the Properties file
	 * */
	public String getErrorFileExtension() {
		String newVal = readProperty(KEY_error_file_extension);
		if(null!=newVal){
			errorFileExtension = newVal;
		}
		return errorFileExtension;
	}

	public String getWebServiceName() {
		String newVal = readProperty(KEY_webservice_name);
		if(null!=newVal){
			webServiceName = newVal;
		}
		return webServiceName;
	}

	public String getWebServiceNamespace() {
		String newVal = readProperty(KEY_webservice_namespace);
		if(null!=newVal){
			webServiceNamespace = newVal;
		}
		return webServiceNamespace;
	}

	public String getParamAppKey() {
		String newVal = readProperty("appKey");
		if(null!=newVal){
			paramAppKey = newVal;
		}
		return paramAppKey;
	}


	public String getParamNetworkType() {
		String newVal = readProperty("networkType");
		if(null!=newVal){
			paramNetworkType = newVal;
		}
		return paramNetworkType;
	}


	public String getParamRegion() {
		String newVal = readProperty("region");
		if(null!=newVal){
			paramRegion = newVal;
		}
		return paramRegion;
	}


	public String getParamUdid() {
		String newVal = readProperty("udid");
		if(null!=newVal){
			paramUdid = newVal;
		}
		return paramUdid;
	}


	public String getParamDeviceType() {
		String newVal = readProperty("deviceType");
		if(null!=newVal){
			paramDeviceType = newVal;
		}
		return paramDeviceType;
	}


	public String getParamDeviceLocale() {
		String newVal = readProperty("deviceLocale");
		if(null!=newVal){
			paramDeviceLocale = newVal;
		}
		return paramDeviceLocale;
	}

	public String getParamClientVersion() {
		String newVal = readProperty("clientVersion");
		if(null!=newVal){
			paramClientVersion = newVal;
		}
		return paramClientVersion;
	}

}