package raz.test.testAndUnderstand;

import java.net.MalformedURLException;
import java.net.URL;

public class ReferenceVsPrimitive {

	public static void main(String[] args) {
		//primitiveInt();
		//stringType();
		//referenceType();
/*		try {
			referenceTypeURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//referenceTypeArray();
		//referenceType_StringBuffer();
		
		understandVariables();
	}

	static void primitiveInt() {

		int y = 5;
		System.out.println("y:" + y);

		int x = y;
		System.out.println("x:" + x);

		/*
		 * y is a primitive variable, any changes to y will not change x
		 */

		y = -1;// Change the value of y
		System.out.println("y:" + y);

		System.out.println("x:" + x);

	}
	
	static void stringType(){
		/**
		 * In addition to the eight primitive data types listed above, the Java
		 * programming language also provides special support for character
		 * strings via the java.lang.String class. Enclosing your character
		 * string within double quotes will automatically create a new String
		 * object
		 * */
		String y = "a";
		System.out.println(y.hashCode()+"y:" + y);
		
		String x = y;//declare a variable x which is a reference to y
		System.out.println(x.hashCode()+"x:" + x);
		
		/*
		 * since y is a reference to 'String Object "a"',
		 * if you change y, it should(?!) change also x
		 * */
		
		y = "b";//Change the value/state of 'String Object "a"'
		/**
		 * so this actually creates a NEW String Object
		 * */
		
		System.out.println(y.hashCode()+"y:" + y);

		System.out.println(x.hashCode()+"x:" + x);//the value of x is still "a" and not "b"
		//Why?
		//Because String is immutable
		/**
		 * String objects are immutable, which means that once created, their
		 * values cannot be changed. The String class is not technically a
		 * primitive data type, but considering the special support given to it
		 * by the language, you'll probably tend to think of it as such.
		 * */
		
	}
	
	static void referenceType_StringBuffer(){
		StringBuffer y = new StringBuffer("abc");
		System.out.println(y.hashCode()+"y:" + y);
		
		StringBuffer x = y;//declare a variable x which is a reference to the same object referenced by y
		System.out.println(x.hashCode()+"x:" + x);
		
		//change x
		x.reverse();
		
		System.out.println(y.hashCode()+"y:" + y);

		System.out.println(x.hashCode()+"x:" + x);
	}
	
	static void referenceType(){
		Exception y = new Exception("a");
		System.out.println(y.hashCode()+"y:" + y);
		
		Exception x = y;//declare a variable x which is a reference to y
		System.out.println(x.hashCode()+"x:" + x);
		
		/*
		 * since y is a reference to 'Exception object "a"',
		 * if you change y, it should change also x
		 * */
		
		y = new Exception("b");/* Change the value/state of 'Exception object "a"' ?!!*/
		//Actually what I am doing is: reference y now points to another Exception Object
		
		System.out.println(y.hashCode()+"y:" + y);

		System.out.println(x.hashCode()+"x:" + x);//the value of x is still "a" and not "b"
			
	}
	
	static void referenceTypeURL() throws MalformedURLException  {
		URL y = new URL("http://www.oracle.com/");//declare a variable y which is a reference to URL Object "http://www.oracle.com/"
		System.out.println(y.hashCode()+"y:" + y);
		
		URL x = y;//declare a variable x which is a reference to the same Object referenced by y
		System.out.println(x.hashCode()+"x:" + x);
		
		/*
		 * Since y is a reference to URL Object "http://www.oracle.com/"
		 * if you change y, it should change also x
		 * */
		//We have only one URL object which has 2 reference variables to it.
		//Change the value/state of the URL Object 
		//- I can't change the URL Object state because URL class doesn't provide any methods to change the object's state 
				
	}
	
	static void referenceTypeArray(){
		char[] y = {'a'};//declare a variable y which is a reference to Array Object
		System.out.print(y.hashCode()+"y:"); printArray(y);
		
		char[] x = y;//declare a variable x which is a reference to the same Object referened by y
		System.out.print(x.hashCode()+"x:"); printArray(x);
		
		//change the value of the y(Change the value/state of the object referenced by y)
		y[0] = 'b';
		/*
		 * since y is a reference to 'Array "a"',
		 * if you change y, it should change also x - which is a reference to the same object
		 * */
		System.out.print(y.hashCode()+"y:"); printArray(y);
		System.out.print(x.hashCode()+"x:"); printArray(x);
	}
	
	static void understandVariables() {
		//https://docs.oracle.com/javase/specs/jls/se5.0/html/typesValues.html#111011
		/**
		 * The types of the Java programming language are divided into two categories: primitive types and reference types. 
		 * The primitive types contain primitive values
		 * Primitive values do not share state with other primitive values. A variable whose type is a primitive type always holds a primitive value of that same type.
		 * The value of a variable of primitive type can be changed only by assignment operations on that variable
		 * 
		 * The reference types are class types, interface types, and array types.
		 * The values of a reference type are references to objects.
		 * The reference values (often just references) are pointers to these objects, and a special null reference, which refers to no object.
		 * */
		
		
		int integerA = 1; //Declare a primitive variable 'integerA' which is intialized with Value 1
		int integerB = integerA;//Declare a primitive variable 'integerB' which is assigned the value of 'integerA'
		
		System.out.println("integerA:"+integerA);
		System.out.println("integerB:"+integerB);
		
		integerB = 4;//'integerB' is assigned a new value
		
		System.out.println("integerA:"+integerA);
		System.out.println("integerB:"+integerB);
		
		Value a = new Value();// 'a' is a reference variable whose value is a reference(a pointer) to a newly Value object created on the heap
		a.val = 5;
		Value b = a;//Declare variable 'b' and assign the value of 'a'(the value of 'a' is a reference(a pointer) to the same Value object)
		System.out.println("a"+"["+System.identityHashCode(a)+"]:"+a.val);
		System.out.println("b"+"["+System.identityHashCode(b)+"]:"+b.val);
		
		/** The reference values (often just references) are pointers to these objects, and a special null reference, which refers to no object. */
		b = null;//'b' is assigned the value null reference
		System.out.println("a"+"["+System.identityHashCode(a)+"]:"+a.val);
		System.out.println("b"+"["+System.identityHashCode(b)+"]:"+b);
	}
	
	static void printArray(char[] arr){
		for(char ch : arr){
			System.out.println(ch);
		}
	}

}

class Value { int val; }


