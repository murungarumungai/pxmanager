package at.apa.pdfwlserver.monitoring.csv;

import java.util.List;

public class CsvRow {
	
	private String issueDate;//mapped to Issue-Date
	private List<String> mutation;//mapped to Mutations
	private String dataProcessed;//mapped to Data processed
	private String dataDueDate;//mapped to Due-Date Data Delivery
	private String dataEarliestDelivery;//mapped to Earliest Data delivery
	
	private static int columnNumber = 5;
	
	public CsvRow(){
		
	}
	
	public CsvRow(String issueDate, List<String> mutation,String dataProcessed,String dataDueDate,String dataEarliestDelivery ){
		this.issueDate = issueDate;
		this.mutation = mutation;
		this.dataProcessed = dataProcessed;
		this.dataDueDate = dataDueDate;
		this.dataEarliestDelivery = dataEarliestDelivery;
	}
	
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public List<String> getMutation() {
		return mutation;
	}
	public void setMutation(List<String> mutation) {
		this.mutation = mutation;
	}
	public String getDataProcessed() {
		return dataProcessed;
	}
	public void setDataProcessed(String dataProcessed) {
		this.dataProcessed = dataProcessed;
	}
	public String getDataDueDate() {
		return dataDueDate;
	}
	public void setDataDueDate(String dataDueDate) {
		this.dataDueDate = dataDueDate;
	}
	public String getDataEarliestDelivery() {
		return dataEarliestDelivery;
	}
	public void setDataEarliestDelivery(String dataEarliestDelivery) {
		this.dataEarliestDelivery = dataEarliestDelivery;
	}

	/**
	 * <p>
	 * the expected number of columns in the .csv
	 * Deault is 5:
	 * Issue-Date
	 * Mutations
	 * Data processed
	 * Due-Date Data Delivery
	 * Earliest Data delivery
	 * </p>
	 * */
	public static int getColumnNumber() {
		return columnNumber;
	}

	public static void setColumnNumber(int columnNumber) {
		CsvRow.columnNumber = columnNumber;
	}
}
