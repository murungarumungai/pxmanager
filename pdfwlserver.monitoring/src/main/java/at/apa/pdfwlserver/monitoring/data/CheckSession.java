package at.apa.pdfwlserver.monitoring.data;

import java.io.File;
import java.util.Date;
import java.util.List;

import at.apa.pdfwlserver.monitoring.utils.DateUtils;

public class CheckSession {
	
	private final Mutation currentCheckedMutation;
	private final Issue currentCheckedIssue;
	/*
	 * dataEarliestDelivery of the next mutation
	 * 
	 * if there is no next mutation return issueDate.23:59 of the current(which should be the last issue in .csv) issue date
	 * */ 
	private final Date nextEarliestDataDelivery;
	//We must trace the data Packets through the importing process during a CheckSession(CheckInterval) of a certain mutation
	//private boolean wasInIncoming = false;
	private File incomingLatestFileWithinCheckInterval;//cache the latestFileWithinCheckInterval so that we can provide some information
	
	/**
	 * Private Constructor to prevent instantiation
	 * */
	private CheckSession(Issue issue, Mutation mutation, Date  nextEarliestDataDelivery){
		this.currentCheckedIssue = issue;
		this.currentCheckedMutation = mutation;
		this.nextEarliestDataDelivery = nextEarliestDataDelivery;
	}
	
	public Mutation getCurrentCheckedMutation(){
		return currentCheckedMutation;
	}
	
	public Issue getCurrentCheckedIssue(){
		return currentCheckedIssue;
	}
	
	/**
	 * Return the next EarliestDataDelivery of the next issue->mutation
	 * Represents the End of the Check interval
	 * */
	public Date getNextEarliestDataDelivery(){
		return nextEarliestDataDelivery;
	}
	
	/**
	 * returns true if data ever existed in incoming folder during this CheckInterval
	 * */
/*	public boolean wasInIncoming(){
		return wasInIncoming;
	}*/
	
	/**
	 * @return Returns the last know file WithinCheckInterval in incoming folder,
	 * if data ever existed in incoming folder during this CheckInterval
	 * */
	public File getIncomingLatestFileWithinCheckInterval(){
		return incomingLatestFileWithinCheckInterval;
	}
	
	/**
	 * asserts that data was in incoming folder during this CheckInterval
	 * */
	public void setIncomingLatestFileWithinCheckInterval(File file){
		this.incomingLatestFileWithinCheckInterval = file;
	}
	
	/**
	 * asserts that data was in incoming folder during this CheckInterval
	 * */
	/*public void setWasInIncoming(){
		wasInIncoming = true;
	}*/
	
	/**
	 * @param now - is the moment in time(currentSystemTime) when the check is performed
	 * <p>
	 * looks into the MonitoringProfile, it iterates through all issues->mutations and it finds the mutation 
	 * for which the check is done in this moment.
	 * <p>
	 * now.after(earliestDataDelivery) && now.before(nextEarliestDataDelivery)
	 * </p>
	 * 
	 * @return may return null is all the mutations(date) are expired, or if the monitoringProfile is null
	 * </p>
	 * */
	public static CheckSession getMutationBeingCheckedRightNow(Date now){
		CheckSession result = null;
		Date earliestDataDelivery;
		Date nextEarliestDataDelivery;
		MonitoringProfile monitoringProfile = MonitoringProfileCache.getMonitoringProfile();
		if(null!=monitoringProfile){
			List<Issue> issues = monitoringProfile.getIssues();
			int issuesLen = issues.size();
			boolean onLastIssue = false;
			
			for(int i =0; i<=issuesLen-1; i++){
				Issue currentIssue = issues.get(i);
				if(i==issuesLen-1){
					onLastIssue = true;
				}
				
				boolean onLastMutation = false;
				List<Mutation> mutations = currentIssue.getMutations();
				int mutationLen = mutations.size();
				
				Mutation nextMutation = null;
				
				for(int j=0; j<=mutationLen-1; j++){
					
					Mutation currentMutation = mutations.get(j);
					if(j==mutationLen-1){
						//we are on the last mutation(we only have one mutation in this issue)
						onLastMutation = true;
					}
					earliestDataDelivery = currentMutation.getDataEarliestDelivery();
					
					if(onLastIssue){
						//we are on the last issue, there is no issue in the list after this
						if(onLastMutation){
							//we are on the last mutation, there is no other entry after this
							//nextEarliestDataDelivery is the end of the day issueDate, i.e. issueDate.23.59 
							nextEarliestDataDelivery = DateUtils.getEndOfDayTime(currentIssue.getIssuseDate());
						} else{
							//there is at least one mutation after this, get the next mutation's earliest data delivery
							nextMutation = mutations.get(j+1);
							//But 1st make sure that we don't have an Issue with multiple mutations with exactly same dates
							if(currentMutation.getDataEarliestDelivery().equals(nextMutation.getDataEarliestDelivery())){
								//We are in the case when we have multiple mutations with the same date
								//since we are onLastIssue 
								nextEarliestDataDelivery = DateUtils.getEndOfDayTime(currentIssue.getIssuseDate());
							} else {
								nextEarliestDataDelivery = nextMutation.getDataEarliestDelivery();
							}
						}
					} else {
						//there is at least one issue after this, get the next issue, if we do not have a next mutation
						if(onLastMutation){
							//we only have one mutation in this issue, so get nextIssue->firstMutation
							Issue nextIssue = issues.get(i+1);
							nextMutation = nextIssue.getMutations().get(0);
							nextEarliestDataDelivery = nextMutation.getDataEarliestDelivery();
						} else{
							//current issue has more than one mutation,
							//so take the next mutation and if it has a different date than currentIssue
							nextMutation = mutations.get(j+1);
							if(currentMutation.getDataEarliestDelivery().equals(nextMutation.getDataEarliestDelivery())){
								//We are in the case when we have multiple mutations with the same date
								//so get nextIssue->firstMutation
								Issue nextIssue = issues.get(i+1);
								nextMutation = nextIssue.getMutations().get(0);
								nextEarliestDataDelivery = nextMutation.getDataEarliestDelivery();
							} else {
								//We are in the case when we have multiple mutations and the next mutation has a different date
								//so take the date of the next issue
								//there is at least one mutation after this, get the next mutation's earliest data delivery								
								nextEarliestDataDelivery = nextMutation.getDataEarliestDelivery();
							}
						}
						
					}
					if( (now.equals(earliestDataDelivery) || now.after(earliestDataDelivery))  && now.before(nextEarliestDataDelivery)){
						//we are within the check interval of this mutation
						result = new CheckSession(currentIssue, currentMutation, nextEarliestDataDelivery);
						return result;
					}
				}
			}
		}
		return result;
	}
	
}
