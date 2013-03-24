package raz.test.enumexercise;

public enum NewIssuesActive {
	
	ALWAYS("always"),
	NEVER("never"),
	AS_PREVIOUS_VERSION("as-previous-version");
	private String newIssuesActive;
	
	 NewIssuesActive(String newIssuesActive){
		this.newIssuesActive = newIssuesActive;
	}
	
	public String getNewIssuesActive(){
		return this.newIssuesActive;
	}
	
	 

}
