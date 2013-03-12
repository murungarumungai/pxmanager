package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import at.apa.pdfwlserver.monitoring.utils.FileUtils;

public class DirectoryFileCheckerImpl implements DirectoryFileChecker {
	
	private File dirPath;
	
	public DirectoryFileCheckerImpl(File dirPath){
		this.dirPath = dirPath;
	}
	
	

	public File getLatestFileWithinCheckInterval(Date earliestDataDelivery, Date nextEarliestDataDelivery) throws IOException {
		
		return FileUtils.getLatestFileFromDir(dirPath, earliestDataDelivery, nextEarliestDataDelivery, MonitoringProfileCache.getMonitoringProfile().getCheckedFileExtension());
	}

	/**
	 * Return the latest file from this directory
	 * @return may return null if directory is empty
	 * */
	public File getLatestFile(){
		return FileUtils.getLatestFileFromDir(dirPath, MonitoringProfileCache.getMonitoringProfile().getCheckedFileExtension());
	}


}
