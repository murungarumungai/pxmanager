package at.apa.pdfwlserver.monitoring;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import at.apa.pdfwlserver.monitoring.csv.CsvParser;
import at.apa.pdfwlserver.monitoring.csv.CsvRow;
import at.apa.pdfwlserver.monitoring.data.Issue;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.data.SubDirChecker;
import at.apa.pdfwlserver.monitoring.utils.FileUtils;
import at.apa.pdfwlserver.monitoring.xml.CustomerBaseDir;
import at.apa.pdfwlserver.monitoring.xml.CustomerDirStructureMarshaler;
import at.apa.pdfwlserver.monitoring.xml.SubDir;

public class MonitoringProfileReaderImpl implements MonitoringProfileReader {
	private File customerBaseDirPath;
	private File xmlFilePath;
	private File csvFilePath;
	List<SubDirChecker> customerFileSystemStructure;
	List<Issue> issues;// issues extracted from .csv
	private File freeMarkerTemplatePath;//path to the freeMarker Template. TODO: should be read from .properties file

	public MonitoringProfileReaderImpl(File customerBaseDir,
			File customerDirConfigXmlFilePath, File csvConfigFilePath) {
		this.customerBaseDirPath = customerBaseDir;
		xmlFilePath = customerDirConfigXmlFilePath;
		csvFilePath = csvConfigFilePath;
	}

	public MonitoringProfile readMonitoringProfile() throws JAXBException, IOException {
		MonitoringProfile result = null;

		// Customer Directory Structure
		CustomerBaseDir customerFoldersJAXB = null;
		customerFoldersJAXB = CustomerDirStructureMarshaler
				.unMarshal(xmlFilePath);
		customerFileSystemStructure = loadCustomerFileSystemStructure(customerFoldersJAXB);

		// List of Issues with their mutations and timepoints from .csv
		List<CsvRow> csvRows = CsvParser.parseCSVFile(csvFilePath);
		List<Issue> issues = CsvParser.loadIssuesFromCsvRows(csvRows);

		// TODO: regularCheckRepeatPeriod should be read from .properties file
		result = new MonitoringProfile(customerFileSystemStructure, issues, 1);
		return result;
	}

	/**
	 * this method loads from CustomerBaseDir(JAXB) - which contains only
	 * String-DirNames - the actually directories on the file system. checks if
	 * all directories configured in the .xml really physically exist on the
	 * file System
	 * @throws IOException 
	 * 
	 * @throws IllegalAccessException
	 * */
	private List<SubDirChecker> loadCustomerFileSystemStructure(
			CustomerBaseDir customerFoldersJAXB) throws IOException {
		
		List<SubDirChecker> result = null;

		String customerBaseDirName = customerFoldersJAXB.getName();
		/*
		 * extract last folder from customerBaseDir CanonicalPath,
		 * if it doesn't equal the one configured in .xml => configuration error
		 * */
		String lastFolderInPath = FileUtils
				.getLastFolderInPath(customerBaseDirPath);

		if (!customerBaseDirName.equalsIgnoreCase(lastFolderInPath)) {
			throw new IllegalArgumentException(
					"Configuration Error! CustomerBaseDir in xml:"
							+ customerBaseDirName + " But customerBaseDirPath:"
							+ customerBaseDirPath.toString());
		}
		result = new ArrayList<SubDirChecker>(customerFoldersJAXB.getSubDir().size());
		SubDirChecker subDirChecker = null;
		String subDirName = null;
		File subDirPath = null;
		List<at.apa.pdfwlserver.monitoring.xml.FileCondition> fileConditionsJAXB = null;
		for(SubDir oneSubDir:customerFoldersJAXB.getSubDir()){
			subDirName = oneSubDir.getName();
			//build the subDirPath from the CustomerBaseDir
			subDirPath = FileUtils.checkDirExists(new File(customerBaseDirPath, subDirName));
			
			fileConditionsJAXB = oneSubDir.getFileCondition();
			
			subDirChecker = new SubDirChecker(subDirPath, null);
			result.add(subDirChecker);
		}
		
		return result;
	}

}
