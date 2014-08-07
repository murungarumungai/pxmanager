package raz.test.collection.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class SetTester {

	public static void main(String[] args) {

		eliminateDuplicates();
		
		setBulkOperations();
	}
	
	private static void setBulkOperations(){
		
		Set<String> setA = new HashSet<String>();
		setA.add("a");
		setA.add("b");
		setA.add("c");
		
		Set<String> setB = new HashSet<String>();
		setB.add("c");
		setB.add("d");
		
		//transforms s1 into the union of s1 and s2. (The union of two sets is the set containing all of the elements contained in either set.) 
		//setA.addAll(setB);
		//System.out.println(setA);
		
		/*
		 * To calculate the Union of two sets nondestructively (without modifying either set),
		 * the caller must copy one set before calling the appropriate bulk operation. 
		 * */
		
		Set<String> union = new HashSet<String>(setA);
		union.addAll(setB);
		System.out.println(union);
		
		Set<String> intersection = new HashSet<String>(setA);
		intersection.retainAll(setB);
		System.out.println(intersection);
		
		Set<String> difference = new HashSet<String>(setA);
		difference.removeAll(setB);
		System.out.println("difference>>"+difference);
	}

	/**
	 * Here I want to eliminate all duplicates.
	 * 
	 * Here's a simple but useful Set idiom. Suppose you have a Collection, c,
	 * and you want to create another Collection containing the same elements
	 * but with all duplicates eliminated. The following one-liner does the
	 * trick.
	 * 
	 * Collection<Type> noDups = new HashSet<Type>(c);
	 * 
	 * */
	private static void eliminateDuplicates() {
		//List<String> input = new ArrayList<String>();
		
		List<String> input = new Vector<String>();

		input.add("a");
		input.add("b");
		input.add("b");
		input.add("c");

		System.out.println("input>>" + input);

		Set<String> noDups = removeDups(input);

		System.out.println("output>>" + noDups);
	}

	public static <E> Set<E> removeDups(Collection<E> collection) {
		return new LinkedHashSet<E>(collection);
	}
	
	
	
	

}
