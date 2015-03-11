package raz.test.collection.set;

import java.util.Random;

/**
 * This class implements string related utilities
 *
 * @author Ciprian Popa (cyparu)
 * @version 1.0, 10.06.2004
 */
public class StringGenerator {



	private static final char[] CHARS = getAlpanumericASCII();
	
	private static char[] getAlpanumericASCII(){
		char[] result = new char[36];
		
		//Numbers
		int index =0;
		for(int i = 48; i<=57; i++){
			result[index++] = (char) i;
		}
		
		//Lower Alphabet
		for(int i=97; i<=122;i++){
			result[index++] = (char) i;
		}
		
		//System.out.println(index);
		
		return result;
	}



	/**
	 * Generate a password with the specified length.
	 *
	 * @param length password length
	 * @return password - generated password
	 */
	public static String generatePassword (int length) {
		Random random = new Random();
		char[] pass = new char[length];
		while (length-- != 0) {
			pass[length] = CHARS[random.nextInt(CHARS.length)];
		}
		return new String(pass);
	}


}
