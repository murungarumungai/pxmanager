package raz.test.collection.map.clone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapComparer {
	
	//http://stackoverflow.com/questions/2817695/how-does-java-order-items-in-a-hashmap-or-a-hashtable
	
	//http://stackoverflow.com/questions/4082416/equality-between-2-hashmap
	
	
	public static void main(String[] args){
		Map<String, List<String>> m1 = getTestMap();
		Map<String, List<String>> m2 = getTestMap2();
		
		boolean areEqual = m1.equals(m2);
		
		//Ma astept sa fie true
		System.out.println(">>"+areEqual);
	}
	
	/**
	 * keyA - AA,BB
	 * keyB - CC
	 * */
	static Map<String, List<String>> getTestMap(){
		Map<String, List<String>> data = new HashMap<String, List<String>>();
		
		List<String> values1 = new ArrayList<String>();
		values1.add("AA");
        values1.add("BB");
		data.put("keyA", values1);
		
		List<String> values2 = new ArrayList<String>();
		values2.add("CC");
		data.put("keyB", values2);
		
		return data;
	}
	
	/**
	 * keyB - CC
	 * keyA - AA,BB
	 * 
	 * */
	static Map<String, List<String>> getTestMap2(){
		Map<String, List<String>> data = new HashMap<String, List<String>>();
		
		List<String> values2 = new ArrayList<String>();
		values2.add("CC");
		data.put("keyB", values2);
		
		List<String> values1 = new ArrayList<String>();
		values1.add("AA");
		values1.add("BB");
		data.put("keyA", values1);
		
		return data;
	}

}
