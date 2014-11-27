package raz.test.String.Manipulation;

import java.text.MessageFormat;

public class MessageFormatDemo {
	
	public static void main(String[] args){
		
		String test = "{0} + {1} = {2}";
		System.out.println(">>"+test);
		test = MessageFormat.format(test, 1,2,1+2);
		
		System.out.println(">>"+test);
	}

}
