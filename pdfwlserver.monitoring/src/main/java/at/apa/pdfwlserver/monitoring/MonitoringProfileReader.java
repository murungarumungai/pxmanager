package at.apa.pdfwlserver.monitoring;

import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;

import javax.xml.bind.JAXBException;

public interface MonitoringProfileReader {
	
	/**
	 * reads the .csv and .xml and instantiate a monitoring profile
	 * */
	MonitoringProfile readMonitoringProfile() throws JAXBException ;

}
