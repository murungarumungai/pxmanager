package raz.test.String.Manipulation;

import java.util.StringTokenizer;

public class StringTokenazierDemo {
	
	public static void main(String[] args){
		
		getParagraphStrToken("M3+M2+T", "+");
		
	}
	
    /**
     * Cred ca e mai buna metoda lui Eugen in care caut cu StringTokenizer dupa
     * tokenul: "</p>" - Sfarsit de paragraf Pentru ca
     */
    
    public static String getParagraphStrToken(String input, String delimiter){
        
        String result = null;
        
        
        
        StringTokenizer tok = new StringTokenizer(input, delimiter);
        
        while (tok.hasMoreElements()){
            String token = tok.nextToken();
            result = token;
            System.out.println(token);
        }
        
        return result;
    }

}
