package raz.test.Monitoring.loader;

import raz.test.Monitoring.MonitoringChecker;
import raz.test.Monitoring.MonitoringProfile;

/**
 * This class just holds and provides instances of MonitoringProfile
 * */
public class MonitoringProfileFactory {
	
	private static MonitoringProfile instance ;
	
	public static void setInstance(MonitoringProfile profile){
		if(null == instance){
			instance = profile;
			/*
			 * This is the 1st time when we instancitate a Monitoring profile
			 * So Just go ahead and start timers without any other checks
			*/
			MonitoringChecker monitoringChecker = new MonitoringChecker(5);
			monitoringChecker.scheduleCheck();
		} else {
			/**
			 * Is the current MonitoringChecker running a check?
			 * */
			
		}
	}
	
	public static MonitoringProfile getInstance(){
		return instance;
	}
	
	/**
	 * each time we set the MonitoringProfile, compare the new profile that is being set,
	 * with the already existing monitoringProfile.
	 * And only if the MonitoringProfile has been updated re-initialize the check task
	 * else just execute the scheduled check
	 * */
	public static boolean isProfileUpdated(){
		return false;
	}

}
