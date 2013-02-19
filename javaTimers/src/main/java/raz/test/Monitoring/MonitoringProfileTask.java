package raz.test.Monitoring;

import raz.test.Monitoring.loader.MonitoringProfileFactory;

public class MonitoringProfileTask implements Runnable{
	
	
	//final MonitoringProfile monitoringProfile;
	
	
	public MonitoringProfileTask(/*MonitoringProfile monitoringProfile*/){
		//this.monitoringProfile = monitoringProfile;
	} 

	public void run() {
		
		MonitoringProfile monitoringProfile = MonitoringProfileFactory.getInstance();
		monitoringProfile.check();
	}

}
