package raz.test.collection.sort.date;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DecisonClient {

	public static void main(String[] args) {

		List<Decision> decisions = new ArrayList<Decision>();

		decisions.add(new Decision("2001.02.06"));
		decisions.add(new Decision("2000.02.06"));
		decisions.add(new Decision());
		
		List<Decision> sortedDecisions = sortASCDecisions(decisions);
		
		System.out.println(sortedDecisions);
	}

	public static List<Decision> sortASCDecisions(List<Decision> decisions) {

		Collections.sort(decisions, new Comparator<Decision>() {

			@Override
			public int compare(final Decision o1, final Decision o2) {
				int resComp = 0;

				if (o1.getDecisionDate() == null
						&& o2.getDecisionDate() == null) {
					resComp = 0;
				} else {// One of them MUST NOT be null
					if (o1.getDecisionDate() == null) {
						resComp = -1;// o1 is null, so it is less than o2
						// negative integer as the first argument is less than
						// the second
					} else if (o2.getDecisionDate() == null) {
						resComp = 1;// o2 is null, so o1 is greater than o2
						// or a positive integer as the first argument greater
						// than the second
					} else {
						resComp = o1.getDecisionDate().compareTo(
								o2.getDecisionDate());
					}
				}
				return resComp;

			}

		});

		return decisions;
	}

}
