package raz.test.String.Manipulation;

public class StringReplace {
	
	static String testStr = "Razvan are un {TITLEOFLEADSTORY} dar mai are inca unul{TITLEOFLEADSTORY}";
	
	
	public static void main(String[] args){
		String out = testStr.replace("{TITLEOFLEADSTORY}", "sampleTitle");
		System.out.println(out);
	}

}
