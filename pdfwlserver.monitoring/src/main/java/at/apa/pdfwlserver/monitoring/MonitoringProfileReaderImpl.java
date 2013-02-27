package at.apa.pdfwlserver.monitoring;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import at.apa.pdfwlserver.monitoring.csv.CsvParser;
import at.apa.pdfwlserver.monitoring.csv.CsvRow;
import at.apa.pdfwlserver.monitoring.data.Issue;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.data.SubDirChecker;
import at.apa.pdfwlserver.monitoring.xml.CustomerBaseDir;
import at.apa.pdfwlserver.monitoring.xml.CustomerDirStructureMarshaler;

public class MonitoringProfileReaderImpl implements MonitoringProfileReader{
	
	private File xmlFilePath ;
	private File csvFilePath;
	List<SubDirChecker> customerFileSystemStructure;
	List<Issue> issues;//issues extracted from .csv
	
	public MonitoringProfileReaderImpl(File customerDirConfigXmlFilePath, File csvConfigFilePath){
		xmlFilePath = customerDirConfigXmlFilePath;
		csvFilePath = csvConfigFilePath;
	}

	
	public MonitoringProfile readMonitoringProfile() throws JAXBException, IOException {
		MonitoringProfile result = null;
		
		//Customer Directory Structure
		CustomerBaseDir customerFoldersJAXB = null;
		customerFoldersJAXB =  CustomerDirStructureMarshaler.unMarshal(xmlFilePath);
		customerFileSystemStructure = loadCustomerFileSystemStructure(customerFoldersJAXB);
		
		//List of Issues with their mutations and timepoints from .csv
		List<CsvRow> csvRows = CsvParser.parseCSVFile(csvFilePath);
		List<Issue> issues = CsvParser.loadIssuesFromCsvRows(csvRows);
		
		
		//TODO: regularCheckRepeatPeriod should be read from .properties file
		result = new MonitoringProfile(customerFileSystemStructure, issues, 1);
		return result;
	}
	
	
	
	
	/**
	 * this method loads from CustomerBaseDirJAXB - which contains only String-DirNames, 
	 * the actually directories on the file system.
	 * checks if all directories configured in the .xml really phisicaly exist on the file System
	 * */
	private List<SubDirChecker> loadCustomerFileSystemStructure(CustomerBaseDir customerFoldersJAXB){
		return customerFileSystemStructure;
	}

}
