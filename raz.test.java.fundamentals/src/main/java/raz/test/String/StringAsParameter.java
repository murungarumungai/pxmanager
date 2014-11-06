package raz.test.String;

public class StringAsParameter {
	
	public static void main(String[] args){
		String param  = "xxx  ";
		checkStringParam(param);
		System.out.println(">"+param+"<");
		
		StringBuffer sb  = new StringBuffer("abc");
		System.out.println(">"+sb+"<");
		checkStringBuffer(sb);
		System.out.println(">"+sb+"<");
	}
	
	
	public static void checkStringParam(String stringKey){
		System.out.println(">"+stringKey+"<");
		stringKey = stringKey.trim();
		System.out.println(">"+stringKey+"<");
		//return stringKey;
	}
	
	public static void checkStringBuffer(StringBuffer sb){
		System.out.println(">"+sb+"<");
		sb.reverse();
		System.out.println(">"+sb+"<");
	}

}
