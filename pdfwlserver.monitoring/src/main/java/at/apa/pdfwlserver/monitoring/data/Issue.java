package at.apa.pdfwlserver.monitoring.data;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * represents a newspaper Issue from .csv
 * - it is usually one issue per row. (one row/issue can contain multiple mutations)
 * - but it can be that we have 2 rows that refer to the same issue, each row containing a different mutation of the same issue
 * </p>
 * */

public class Issue {
	
	private Date issuseDate;
	private List<Mutation> mutations;
	
	public Issue(Date issuseDate, List<Mutation> mutations){
		this.issuseDate = issuseDate;
		this.mutations = mutations;
	}
	
	public Date getIssuseDate() {
		return issuseDate;
	}
	public void setIssuseDate(Date issuseDate) {
		this.issuseDate = issuseDate;
	}
	public List<Mutation> getMutations() {
		return mutations;
	}
	public void setMutations(List<Mutation> mutations) {
		this.mutations = mutations;
	}
	
	
}
