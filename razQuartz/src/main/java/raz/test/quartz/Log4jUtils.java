package raz.test.quartz;

import org.apache.log4j.BasicConfigurator;

public class Log4jUtils {
	
	/**
	 * Enable Log4J with a basic default configuration (console only).
	 */
	public static void configureBasicLogging() {
		try {
			BasicConfigurator.configure();
		} catch (Exception e) {
		}
	}

}
