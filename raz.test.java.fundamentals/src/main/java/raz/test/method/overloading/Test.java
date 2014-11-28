package raz.test.method.overloading;

public class Test {

	static void testType(Integer i){
		System.out.println("integer");
		System.out.println(i.getClass());
	}
	
	static void testType(long x){
		System.out.println("long");
		
	}
	
	public static void main(String[] args){
		int i = 2;
		testType(i);
		
		testEquality();
	}
	
	public static void testEquality(){
		Integer i1 = 500;
		Integer i2 = 500;
		Integer i3 = 1;
		Integer i4 = 1;
		
		// Compare this output:
		System.out.println(i1 == i2);
		System.out.println(i1.hashCode());
		System.out.println(System.identityHashCode(i1));
		System.out.println(i2.hashCode());
		System.out.println(System.identityHashCode(i2));
		
		System.out.println(i3 == i4);
		System.out.println(System.identityHashCode(i3));
		System.out.println(System.identityHashCode(i4));
	}
}
