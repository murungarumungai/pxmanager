package raz.test.enumexercise;


public class EnumTest {
	
	public static void main(String[] args){
		
		
		String input = "never";
		
		NewIssuesActive inputEnm = NewIssuesActive.valueOf(input);
		
		switch(inputEnm){
			
		case NEVER:
			System.out.println("never");
			break;
		case ALWAYS:
			System.out.println("always");
			break;
		default:
			System.out.println("DEFAULT");
		}
		
	}

}
