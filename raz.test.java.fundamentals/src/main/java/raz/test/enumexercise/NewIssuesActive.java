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
	
	public static NewIssuesActive fromValue(String val){
		for(NewIssuesActive item : NewIssuesActive.values()){
			if(item.newIssuesActive.equals(val)){
				return item;
			}
		}
		throw new IllegalArgumentException(val);
	}
	 

}
