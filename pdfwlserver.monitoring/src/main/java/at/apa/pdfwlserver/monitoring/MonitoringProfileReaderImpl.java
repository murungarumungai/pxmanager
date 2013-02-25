package at.apa.pdfwlserver.monitoring;

import java.io.File;

import javax.xml.bind.JAXBException;

import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.xml.CustomerBaseDir;
import at.apa.pdfwlserver.monitoring.xml.CustomerDirStructureMarshaler;

public class MonitoringProfileReaderImpl implements MonitoringProfileReader{
	
	private File xmlFilePath ;
	
	public MonitoringProfileReaderImpl(File customerDirConfigXmlFilePath, File csvConfigFilePath){
		xmlFilePath = customerDirConfigXmlFilePath;
	}

	
	public MonitoringProfile readMonitoringProfile() throws JAXBException {
		MonitoringProfile result = null;
		
		CustomerBaseDir customerFoldersJAXB = null;
		
		customerFoldersJAXB =  CustomerDirStructureMarshaler.unMarshal(xmlFilePath);
		
		//TODO: regularCheckRepeatPeriod should be read from .properties file
		result = new MonitoringProfile(customerFoldersJAXB, 1);
		return result;
	}
	
	
	private  MonitoringProfile fillUpDirStructure(MonitoringProfile monitoringProfile, CustomerBaseDir customerFoldersJAXB){
		return monitoringProfile;
	}

}
