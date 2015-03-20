package raz.test.testAndUnderstand;

import java.util.HashMap;
import java.util.Map;

public class ModifyThruAccessor {
	
	private Map<String, String> additional = new HashMap<String, String>();
	
	private int integer = -1;

	public Map<String, String> getAdditional() {
		return additional;//'additional' is a reference XX0
	}
	
	public int getInteger() {
		return integer;
	}

//	public void setInteger(int integer) {
//		this.integer = integer;
//	}
	

	public void printProperties(){
		System.out.println(additional);
		System.out.println(integer);
	}

	
	public static void main(String[] args){
		
		
		ModifyThruAccessor obj = new ModifyThruAccessor();
		
		obj.printProperties();
		
		Map<String, String> ref;//'ref' is a reference XX1 
		ref = obj.getAdditional();//'ref' XX1 is not equal to XX0(additional). It just points to the same object as XX0(aditional)
		System.out.println("1 ref:"+ref);
		
		ref = null;//Here you do not change 'additional'(XX0) and point it to null
		//But what you do is that you change just 'ref'(XX1) to point to null. 'additional' still points to a HashMap it was initialized
		
		System.out.println("2 ref:"+ref);
		obj.printProperties();
		
		//now try to change 'integer' thru setter
		//-----------------------------------------------
		
		int integer = obj.getInteger();
		System.out.println("1 integer:"+integer);
		
		integer = 5;//La linia asta de cod, imi este clar ca nu am nici o treaba si aici intuitiv imi este clar ca nu modific ModifyThruAccessor.integer
		System.out.println("2 integer:"+integer);
		obj.printProperties();
		
		changeVariable();
		
	}
	
	public static void changeVariable(){
		
		int integerA = -1;		
		int integerB = integerA;
		System.out.println("integerA:"+integerA);
		System.out.println("integerB:"+integerB);
		
		integerB = 5;
		System.out.println("integerA:"+integerA);
		System.out.println("integerB:"+integerB);
		
	}
}


