package raz.test.collection.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Using Collections.sort() and Comparator in Java
 * http://www.vogella.com/blog/2009/08/04/collections-sort-java/
 * */

public class Simple {

	public static void main(String[] args) {

		//orderAsc();
		//orderDes();
		orderAscRank();
	}

	public static void orderAsc() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(7);
		list.add(2);
		list.add(2);
		list.add(1);

		/*
		 * This is possible because Integer implements the Comparable interface.
		 * This interface defines the method compare which performs pairwise
		 * comparison of the elements and returns -1 if the element is smaller
		 * then the compared element, 0 if it is equal and 1 if it is larger.
		 */

		Collections.sort(list); //ASC
		//Collections.sort(list, Collections.reverseOrder()); // DESC

		for (Integer integer : list) {
			System.out.println(integer);
		}

	}

	public static void orderAscRank(){
		List<Integer> teamPosition = new ArrayList<Integer>();
		
		teamPosition.add(3);
		teamPosition.add(2);
		teamPosition.add(1);
		
		Collections.sort(teamPosition, new MyIntComparable());
		
		for (Integer integer : teamPosition) {
			System.out.println(integer);
		}
	}
	
	public static void orderDes() {
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(4);
//		list.add(3);
//		list.add(7);
//		list.add(2);
//		list.add(2);
//		list.add(1);
		
		Collections.sort(list, new MyIntComparable());
		
		for (Integer integer : list) {
			System.out.println(integer);
		}
		
	}

	static class MyIntComparable implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1-o2;//ASC
		}
	}

}
