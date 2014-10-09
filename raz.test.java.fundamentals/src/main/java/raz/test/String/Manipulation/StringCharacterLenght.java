package raz.test.String.Manipulation;

import java.io.UnsupportedEncodingException;

public class StringCharacterLenght {
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		String str = "a";
		/* Returns the length of this string. The length is equal to the number of Unicode code units in the string.*/
		int charactersLen = str.length(); 
		System.out.println("Numar de caractere:"+charactersLen);//1
		
		byte[] byteArray = str.getBytes("UTF-8");
		char aChar = str.charAt(0);
		System.out.println("Character:"+aChar+" in ASCII "+(int) aChar);//1
		System.out.println("Numar de bytes:"+byteArray.length);//1
		
		String str1 = "ț";
		System.out.println("Numar de caractere:"+str1.length());//1
		byteArray = str1.getBytes("UTF-8");
		System.out.println("Numar de bytes:"+byteArray.length);//2
	}
	
    /**
     * Trim text - in DB we have limited length
     * 
     * @param str
     * @param len
     * @return
     */
    public static String trimString(String str, int len) {
        if (str != null) {
            byte[] b;
            try {
                b = str.getBytes("UTF-8");
                int l = str.length();
                if (b != null && b.length > len) {
                    do {
                        str = str.substring(0, l-1);
                        b = str.getBytes("UTF-8");
                        l--;
                    } while (b.length > len);
                }
                return str;
            } catch (Exception e) {
                //logger.error("Cannot trim", e);
                return null;
            }
        }
        return null;
    }

}
