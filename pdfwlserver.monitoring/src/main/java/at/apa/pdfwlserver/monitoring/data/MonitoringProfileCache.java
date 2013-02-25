package at.apa.pdfwlserver.monitoring.data;



/**
 * This class is a "cache" that holds just one instance of MonitoringProfile.
 * we must always have one and only one MonitoringProfile
 * */

public class MonitoringProfileCache {
	
	
	private static MonitoringProfile instance;
	
	private static boolean profileWasUpdated = true;//we assume that profile is always updated.(defensive/safe approach)
	
	/**
	 * TODO: this should be thread safe. 
	 * Make sure that when we write a new value,
	 * another thread will attempt to read it
	 * Razvan: synchronized is brutal/primitive solution but it will do!
	 * */
	public static synchronized void  setInstance(MonitoringProfile newInstance){
		/* Before setting the profile, check is the newProfile that we are about to set is different than 
		 * current profile.
		 * ONLY if is DIFFERENT we should bother we stopping current check() job, 
		 * and re-instantiate it with the new profile 
		 * */
		//If both profiles equal => profile was NOT updated
		if( instance==null){//if instance is null, set it
			instance = newInstance;
		} else {
			profileWasUpdated = !instance.equals(newInstance);
			if(profileWasUpdated){//only if profile was updated set the instance
				instance = newInstance;
			}
		}
	}
	
	public static synchronized MonitoringProfile getInstance(){
		return instance;
	}
	
	/**
	 * each time we set the MonitoringProfile, compare the new profile that is being set,
	 * with the already existing monitoringProfile.
	 * And only if the MonitoringProfile has been updated re-initialize the check task
	 * else just execute the scheduled check
	 * */
	public static synchronized boolean isProfileUpdated(){
		return profileWasUpdated;
	}
	

}
