package at.apa.pdfwlserver.monitoring.data;

import java.util.Date;

public interface AvailableMutationChecker {
	
	/**
	 * Availability of Mutation check interval is
	 *  [Data processed]{01.01.2013 04:30}-[Data processed of the next entry]{01.01.2013 23:00}
	 *  
	 *  What should be the correct interval for checking the availability of a mutation?
	 *  01.01.2013;"Morning";01.01.2013 04:30;01.01.2013 04:00;01.01.2013 00:00
		02.01.2013;"Evening";01.01.2013 23:00;01.01.2013 21:30;01.01.2013 17:00
		02.01.2013;"Morning";02.01.2013 04:30;02.01.2013 04:00;02.01.2013 00:00
		
		ar trebui sa fie localFromDate = EarliestDataDelivery, pentru ca the best case scenario is that a issue is produced and is available once
		the dataHasBeenReceived.
		
		localToDate ar trebui sa fie:
		a) nextEarliestDataDelivery pentru ca un issue ar trebuie sa fie disponibil pentru client atat timp cat am inceput sa producem celalat issue
		b) pe tot parcursul zilei issue-date, pt ca pentru un newspaper issue, acesta e de actualitate timp de o zi, dupa aceea nu mai e de actualitate
		
		what do the specifications say: Check for available Mutations for the certain issue-date (from 00:00 to 23:59).
	 *  
	 * */
	public void checkMutationAvailability(Date localFromDate, Date localToDate);

}
