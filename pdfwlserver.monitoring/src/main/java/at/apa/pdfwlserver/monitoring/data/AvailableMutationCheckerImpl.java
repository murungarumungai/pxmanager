package at.apa.pdfwlserver.monitoring.data;

import java.util.Date;

import at.apa.pdfwlserver.monitoring.utils.PropertiesReader;

public class AvailableMutationCheckerImpl implements AvailableMutationChecker {

	public void checkMutationAvailability(Date fromDate, Date toDate) {
		String localFromDate = null; //TODO use formatDate(final Date date)
		String localToDate = null;
		
		PropertiesReader propertiesReader = MonitoringProfileCache.getMonitoringProfile().getPropertiesReader();
		
		String appKey = propertiesReader.getParamAppKey();
		String networkType = propertiesReader.getParamNetworkType();
		String region = propertiesReader.getParamRegion();
		String udid = propertiesReader.getParamUdid();
		String deviceType =  propertiesReader.getParamDeviceType();
		String deviceLocale = propertiesReader.getParamDeviceLocale();
		String clientVersion = propertiesReader.getParamClientVersion();
		
	}
	

}
