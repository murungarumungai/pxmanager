package at.apa.pdfwlserver.monitoring;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import at.apa.pdfwlserver.monitoring.csv.CsvParser;
import at.apa.pdfwlserver.monitoring.csv.CsvRow;
import at.apa.pdfwlserver.monitoring.data.DirectoryFileCondition;
import at.apa.pdfwlserver.monitoring.data.ErrorDirectoryFileCondition;
import at.apa.pdfwlserver.monitoring.data.IncomingDirectoryFileCondition;
import at.apa.pdfwlserver.monitoring.data.Issue;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.data.SubDirChecker;
import at.apa.pdfwlserver.monitoring.utils.FileUtils;
import at.apa.pdfwlserver.monitoring.utils.PropertiesReader;
import at.apa.pdfwlserver.monitoring.xml.CustomerBaseDir;
import at.apa.pdfwlserver.monitoring.xml.CustomerDirStructureMarshaler;
import at.apa.pdfwlserver.monitoring.xml.FileCondition;
import at.apa.pdfwlserver.monitoring.xml.SubDir;

public class MonitoringProfileReaderImpl implements MonitoringProfileReader {
	private File propertiesFilePath;
	private File customerBaseDirPath;
	private File xmlFilePath;
	private File csvFilePath;
	
	private File freeMarkerTemplatePath;

	/**
	 * customerBaseDir - the Path to customer base directory
	 * */
	public MonitoringProfileReaderImpl(File configPropertiesFilePath, File customerBaseDir,
			File customerDirConfigXmlFilePath, File csvConfigFilePath) {
		propertiesFilePath = configPropertiesFilePath;
		customerBaseDirPath = customerBaseDir;
		xmlFilePath = customerDirConfigXmlFilePath;
		csvFilePath = csvConfigFilePath;
	}

	public MonitoringProfile readMonitoringProfile() throws JAXBException, IOException {
		MonitoringProfile result = null;

		//Read Configuration properties file 
		PropertiesReader propsReader = PropertiesReader.getInstance(propertiesFilePath);
		
		// Customer Directory Structure
		List<SubDirChecker> customerFileSystemStructure = null;
		CustomerBaseDir customerFoldersJAXB = null;
		customerFoldersJAXB = CustomerDirStructureMarshaler
				.unMarshal(xmlFilePath);
		customerFileSystemStructure = loadCustomerFileSystemStructure(customerFoldersJAXB);

		// List of Issues with their mutations and timepoints from .csv
		List<CsvRow> csvRows = CsvParser.parseCSVFile(csvFilePath);
		List<Issue> issues = CsvParser.loadIssuesFromCsvRows(csvRows);

		
		result = new MonitoringProfile(customerFileSystemStructure, issues, propsReader /*1*/ /*propsReader.getRegularCheckRepeatPeriod(), propsReader.getCheckedFileExtension(), propsReader.getErrorFileExtension()*/);
		return result;
	}

	/**
	 * <p>
	 * This method loads from CustomerBaseDir(JAXB) - which contains only
	 * String-DirNames - the actually directories on the file system (checks if
	 * all directories configured in the .xml really physically exist on the
	 * file System)
	 * </p>
	 * <p>
	 * It also convert the xml.FileCondition(JAXB = are just JAXB artifacts) of each folder into data.FileContions - which tell
	 * what conditions are configured for this subDir and what is the returned status if the condition is fulfilled.
	 * If the subDir has more than one condition, these are mutually exclusive, 
	 * that is, only one of these condition should be true 
	 * </p>
	 * @throws IOException 
	 * 
	 * @throws IllegalAccessException
	 * */
	private List<SubDirChecker> loadCustomerFileSystemStructure(
			CustomerBaseDir customerFoldersJAXB) throws IOException {
		
		List<SubDirChecker> customerBaseDirStructure = null;

		String customerBaseDirName = customerFoldersJAXB.getName();
		/*
		 * extract last folder from customerBaseDir CanonicalPath,
		 * if it doesn't equal the one configured in .xml => configuration error
		 * */
		String lastFolderInPath = FileUtils
				.getLastFolderInPath(customerBaseDirPath);
		if (!customerBaseDirName.equalsIgnoreCase(lastFolderInPath)) {
			throw new IllegalArgumentException(
					"CustomerBaseDir does not match! Configuration Error! CustomerBaseDir in xml:"
							+ customerBaseDirName + " But customerBaseDirPath:"
							+ customerBaseDirPath.toString());
		}
		
		customerBaseDirStructure = new ArrayList<SubDirChecker>(customerFoldersJAXB.getSubDir().size());
		SubDirChecker subDirChecker = null;
		
		for(SubDir oneSubDir:customerFoldersJAXB.getSubDir()){
			
			subDirChecker = getSubDirChecker(oneSubDir);
			customerBaseDirStructure.add(subDirChecker);
		}
		
		return customerBaseDirStructure;
	}
	
	private SubDirChecker getSubDirChecker(SubDir subDirJAXB) throws IOException{
		SubDirChecker subDirChecker = null;
		
		String subDirName = subDirJAXB.getName();
		//build the subDirPath from the CustomerBaseDir
		File subDirPath = FileUtils.checkDirExists(new File(customerBaseDirPath, subDirName)); 
		
		List<FileCondition> fileConditionsJAXB = subDirJAXB.getFileCondition();
		
		List<DirectoryFileCondition> subDirFileConditions = new ArrayList<DirectoryFileCondition>(fileConditionsJAXB.size());
		DirectoryFileCondition subDirFileCondition = null;
		for(FileCondition condition : fileConditionsJAXB){
			if(subDirName.equalsIgnoreCase(SubDirChecker.INCOMING)){
				subDirFileCondition = new IncomingDirectoryFileCondition(condition.isExists(), condition.isWithinTimePoint(), condition.getStatus(), subDirPath);				
			} else if(condition.getErrorSpecialHandling()!=null){
				subDirFileCondition = new ErrorDirectoryFileCondition(condition.isExists(), condition.isWithinTimePoint(), condition.getStatus(), subDirPath, condition.getErrorSpecialHandling());
			}else{
				subDirFileCondition = new DirectoryFileCondition(condition.isExists(), condition.isWithinTimePoint(), condition.getStatus(), subDirPath);
			}
			subDirFileConditions.add(subDirFileCondition);
		}
		
		subDirChecker = new SubDirChecker(subDirFileConditions);
		
		return subDirChecker;
	}

}
