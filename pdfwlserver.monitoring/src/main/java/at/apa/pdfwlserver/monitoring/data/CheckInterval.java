package at.apa.pdfwlserver.monitoring.data;

import java.util.Date;
import java.util.List;

import at.apa.pdfwlserver.monitoring.utils.DateUtils;

public class CheckInterval {
	
	private final Mutation currentCheckedMutation;
	private final Issue currentCheckedIssue;
	/*
	 * dataEarliestDelivery of the next mutation
	 * 
	 * if there is no next mutation return issueDate.23:59 of the current(which should be the last) issue date
	 * 
	 * TODO: 
	 * 
	 * 
	 * 2.b now() == nextEarliestDelivery
	 * 
	 *  TODO: cuta si itereaza numai daca now() nu se gaseste i
	 * */ 
	private final Date nextEarliestDataDelivery; 
	
	/**
	 * Private Constructor to prevent instantiation
	 * */
	private CheckInterval(Issue issue, Mutation mutation, Date  nextEarliestDataDelivery){
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
	 * Return the next EarliestDataDelivery of the next issue/mutation
	 * */
	public Date getNextEarliestDataDelivery(){
		return nextEarliestDataDelivery;
	}
	
	
	/**
	 * @param now - is the moment in time(currentSystemTime) when the check is performed
	 * <p>
	 * looks into the MonitoringProfile, it iterates through all issues.mutations and it finds the mutation 
	 * for which the check is done in this moment
	 * 
	 * @return may return null is all the mutations(date) are expired
	 * </p>
	 * */
	public static CheckInterval getMutationBeingCheckedRightNow(Date now){
		CheckInterval result = null;
		MonitoringProfile monitoringProfile = MonitoringProfileCache.getMonitoringProfile();
		if(null!=monitoringProfile){
			List<Issue> issues = monitoringProfile.getIssues();
			
			int issuesLen = issues.size();
			
			Date earliestDataDelivery;
			Date nextEarliestDataDelivery;
			boolean onLastIssue = false;
			
			for(int i =0; i<=issuesLen-1; i++){
				boolean onLastMutation = false;
				Issue currentIssue = issues.get(i);
				if(i==issuesLen-1){
					onLastIssue = true;
				}
				List<Mutation> mutations = currentIssue.getMutations();
				int mutationLen = mutations.size();
				Date firstMutationDateOfCurrentIssue = null;
				Mutation nextMutation = null;
				for(int j=0; j<=mutationLen-1; j++){
					firstMutationDateOfCurrentIssue = mutations.get(0).getDataEarliestDelivery();
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
							//nextEarliestDataDelivery =  23.59 from issueDate
							nextEarliestDataDelivery = DateUtils.getEndOfDayTime(currentIssue.getIssuseDate());
						} else{
							//there is at least one mutation after this, get the next mutation's earliest data delivery
							//But 1st make sure that we don't have an Issue with multiple mutations with exactly same dates
							
							//TODO this doesn't cover the case when we have multiple mutations with the same dates
							 nextMutation = mutations.get(j+1);
							
							if(firstMutationDateOfCurrentIssue.equals(nextMutation.getDataEarliestDelivery())){
								//We are in the case when we have multiple mutations with the same date
								//so take the 1st mutation of the next issue
								Issue nextIssue = issues.get(i+1);
								 nextMutation = nextIssue.getMutations().get(0);
								nextEarliestDataDelivery = nextMutation.getDataEarliestDelivery();
							} else {
								nextEarliestDataDelivery = nextMutation.getDataEarliestDelivery();
							}
						}
					} else {
						//there is at least one issue after this, get the next issue
						Issue nextIssue = issues.get(i+1);
						 nextMutation = nextIssue.getMutations().get(0);
						nextEarliestDataDelivery = nextMutation.getDataEarliestDelivery();
					}
					
					if( (now.equals(earliestDataDelivery) || now.after(earliestDataDelivery))  && now.before(nextEarliestDataDelivery)){
						//we are within the check interval of this mutation
						result = new CheckInterval(currentIssue, currentMutation, nextEarliestDataDelivery);
						return result;
					}
				}
			}
		}
		return result;
	}
	
}
