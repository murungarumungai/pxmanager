package at.apa.pdfwlserver.monitoring.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.apa.pdfwlserver.monitoring.data.Issue;
import at.apa.pdfwlserver.monitoring.data.Mutation;
import at.apa.pdfwlserver.monitoring.utils.DateUtils;
import at.apa.pdfwlserver.monitoring.utils.FileUtils;

public class CsvParser {
	
	private static Logger logger = LoggerFactory.getLogger(CsvParser.class);
	
	/**
     * The default separator used
     * when building CSV items.
     */
	public static final String SEPARATOR = ";";
	
	/**
	 * Mutations in CSV: There could be more than one mutation in every row. The mutations are comma-separated. 
	 * */
	public static final String MUTATION_SEPARATOR = ",";


	/**
	 * <p>
	 * Usually the 1st line is the header of the .csv file, e.g <code>"Issue-Date";"Mutations";"Data processed";"Due-Date Data Delivery";"Earliest Data delivery"</code> 
	 * and it should be removed by the caller.
	 * The actual data begins with the second line
	 * </p>
	 * <p>
	 * it should not return null. 
	 * it does not silently fails. if we can't read the file, we throw exception
	 * </p>
	 * */
	public static List<CsvRow> parseCSVFile(File filePath) throws IOException {
		
		List<CsvRow> result = new ArrayList<CsvRow>();
		Charset charset = FileUtils.getDefaultCharset();
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		
		inputStream = new FileInputStream(filePath);
		
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset), 10000);
		
		try{
			String vLine = bufferedReader.readLine();
			while(vLine!=null){
				result.add(parse(vLine));
				// Get the next line from the stream.
				vLine = bufferedReader.readLine();
			}
		}finally{
			if(bufferedReader!=null){
				try{bufferedReader.close();}
				catch(IOException ioe){/*ignored*/}
			}
		}
		return result;
	}
	/**
	 * builds issues from csvRows
	 * <p>
	 * - it is usually one issue per row. (one row/issue can contain multiple mutations)
	 * </p>
	 * <p>
	 * - but it can be that we have 2 rows that refer to the same issue(date), each row containing a different mutation of the same issue
	 * in this case we group issues by issueDate
	 * </p>
	 * */
	public static List<Issue> loadIssuesFromCsvRows(List<CsvRow> csvRows){
		List<Issue> issues = null; 
		Map<Date, Issue> issuesGroupedByDate = new HashMap<Date, Issue>();
		
		//Group issues by issuDate
		for(CsvRow oneRow:csvRows){
			Issue tempIssue = getIssue(oneRow) ;
			
			if(issuesGroupedByDate.containsKey(tempIssue.getIssuseDate())){
				//We already have this issue(date)
				Issue issue = issuesGroupedByDate.get(tempIssue.getIssuseDate());
				//add the mutations to the already existing issue(date)
				issue.getMutations().addAll(tempIssue.getMutations());
				
			} else{
				issuesGroupedByDate.put(tempIssue.getIssuseDate(), tempIssue);
			}
			
		}
		
		issues = new ArrayList<Issue>(issuesGroupedByDate.values());
		
		return issues;
	}
	
	private static Issue getIssue(CsvRow csvRow){
		Issue issue = null;
		Date issueDate = DateUtils.parseDate(csvRow.getIssueDate());
		List<Mutation> mutationsPerRow = new ArrayList<Mutation>();
		
		List<String> mutations = csvRow.getMutation();
		Date dataProcessed = DateUtils.parseDate(csvRow.getDataProcessed());
		Date dataDueDate = DateUtils.parseDate(csvRow.getDataDueDate());
		Date dataEarliestDelivery = DateUtils.parseDate(csvRow.getDataEarliestDelivery());
		
		/*
		 * if we have multiple mutations on the same row, it means that all the mutations have the same time-points:dataProcessed, dataDueDate, dataErliestDelivery
		 * */
		Mutation mutation = null;
		for(String oneMutation : mutations){
			mutation = new Mutation(oneMutation, dataProcessed, dataDueDate, dataEarliestDelivery);
			mutationsPerRow.add(mutation);
		}
		
		issue = new Issue(issueDate, mutationsPerRow);
		return issue;
	}


	/**
	 * Converts the given csv line into a Java Object
	 * might return null in case that a csv line doesnt conform to CSV File Structure line numbers
	 * */
	private static CsvRow parse(String oneLine){
		CsvRow result = null;
		
		String[] lineAtoms = oneLine.trim().split(SEPARATOR);
		
		if(lineAtoms.length!=CsvRow.getColumnNumber()){
			logger.warn("Encountered a CSV Row that has a different columnNumber than the Csv File Strucrure, ignoring it! Entry:"+lineAtoms[0]);
		} else{
			
			String issueDate =  lineAtoms[0];
			String mutation = lineAtoms[1];
			String dataProcessed = lineAtoms[2];
			String dueDateDataDelivery = lineAtoms[3];
			String earliestDataDelivery = lineAtoms[4];
			
			String[] mutations = mutation.split(MUTATION_SEPARATOR);
			List<String> mutationList = new ArrayList<String>();
			for(String oneMutation:mutations){
				mutationList.add(oneMutation.trim());
			}
			
			result = new CsvRow(issueDate, mutationList, dataProcessed, dueDateDataDelivery, earliestDataDelivery);
			
		}
		return result;
	}


}
