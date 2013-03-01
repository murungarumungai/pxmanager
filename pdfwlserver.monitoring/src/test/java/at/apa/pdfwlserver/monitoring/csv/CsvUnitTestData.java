package at.apa.pdfwlserver.monitoring.csv;

import java.util.ArrayList;
import java.util.List;

import at.apa.pdfwlserver.monitoring.data.Issue;
import at.apa.pdfwlserver.monitoring.data.Mutation;
import at.apa.pdfwlserver.monitoring.utils.DateUtils;

public class CsvUnitTestData {
	
	public static List<Issue> getExpectedIssues(){
		
		
		List<Issue> data = new ArrayList<Issue>();
		
		
		List<Mutation> mutations1 = getMutationList(
				getMutation("Morning", "01.01.2013 04:30", "01.01.2013 04:00", "01.01.2013 00:00")
				);
		Issue  issue1 = new Issue(DateUtils.parseDate("01.01.2013"), mutations1);
		
		List<Mutation> mutations2 = getMutationList(
				getMutation("Evening", "01.01.2013 23:00", "01.01.2013 21:30", "01.01.2013 17:00"),
				getMutation("Morning", "02.01.2013 04:30", "02.01.2013 04:00", "02.01.2013 00:00")
				);
		Issue issue2 = new Issue(DateUtils.parseDate("02.01.2013"), mutations2);
		
		
		List<Mutation> mutations3 = getMutationList(
				getMutation("Morning"		, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 17:00"),
				getMutation("Lower_Austria"	, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 17:00"),
				getMutation("Upper_Austria"	, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 17:00")
				);
		Issue issue3 = new Issue(DateUtils.parseDate("03.01.2013"), mutations3);
		
		Issue issue4 = new Issue(DateUtils.parseDate("04.01.2013"), mutations3);
		
		List<Mutation> mutations5 = getMutationList(
				getMutation("Morning"		, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 17:00"),
				getMutation("Wiena"			, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 17:00"),
				getMutation("Upper_Austria"	, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 17:00")
				);
		Issue issue5 = new Issue(DateUtils.parseDate("01.02.2013"), mutations5);
		
		data.add(issue1);
		data.add(issue2);
		data.add(issue3);
		data.add(issue4);
		data.add(issue5);
		
		return data;
	}
	
	public static List<Mutation> getMutationList(Mutation... mutations){
		List<Mutation> result = new ArrayList<Mutation>();
		for(Mutation  oneMutation : mutations){
			result.add(oneMutation);
		}
		return result;
	}
	
	public static Mutation getMutation(String name, String dataProcessed, String dueDateDelivery, String earliestDelivery){
		Mutation result = null;
		result = new Mutation(name, DateUtils.parseDateTime(dataProcessed), DateUtils.parseDateTime(dueDateDelivery), DateUtils.parseDateTime(earliestDelivery));
		return result;
	}
	
	

}
