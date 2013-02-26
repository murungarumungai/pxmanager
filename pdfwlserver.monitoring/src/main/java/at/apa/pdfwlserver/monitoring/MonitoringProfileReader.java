package at.apa.pdfwlserver.monitoring;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;

public interface MonitoringProfileReader {
	
	/**
	 * <p>
	 * reads the 
	 * .csv
	 * .xml
	 * .properties and instantiate a monitoring profile
	 * </p>
	 * */
	MonitoringProfile readMonitoringProfile() throws JAXBException, IOException ;

}
