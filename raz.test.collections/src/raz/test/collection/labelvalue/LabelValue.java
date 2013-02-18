package raz.test.collection.labelvalue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple, immutable value object for holding a label/value pair.
 * The label is usually used for presentation purposes
 * whereas the value is used for storage purposes.
 *
 * @author <a href="mailto:robert.handschmann@sphinx.at">Robert Handschmann</a>
 */

/**
 * Razvan: Immutable because once it has been instantiated, it's state can not
 * be altered through setters
 * */

public class LabelValue implements Comparable<Object> {

	private final String label;
	private final Object value;

	public LabelValue(String pLabel, Object pValue) {
		label = pLabel;
		value = pValue;
	}

	public String getLabel() {
		return label;
	}
	
	public Object getValue() {
		return value;
	}

	@Override
	public int compareTo(Object object) {
		
		 return getLabel().compareToIgnoreCase(  ( (LabelValue) object).getLabel().toString());
	}
	
	@Override
	public String toString(){
		return label+"("+value+")";
	}
	
	public static void main(String[] args){
		//testStringComparable();
		testComparable();
	}
	
	
	public static void testComparable(){
		List<LabelValue> objects = new ArrayList<LabelValue>();
		objects.add(new LabelValue("dd", "value_dd"));
		objects.add(new LabelValue("cc", "value_cc"));
		objects.add(new LabelValue("Abc", "value_Abc"));
		objects.add(new LabelValue("d", "value_d"));
		objects.add(new LabelValue("Aab", "value_Aab"));
		
		printList(objects);
		System.out.println("Sorting...");
		Collections.sort(objects);
		printList(objects);
	}
	
	public static void testStringComparable(){
		List<String> names = new ArrayList<String>();
		names.add("dd");
		names.add("cc");
		names.add("Abc");
		names.add("aa");
		names.add("Aab");
		names.add("Abd");
		
		printList(names);
		System.out.println("Sorting...");
		Collections.sort(names);
		printList(names);
	}
	
	static void printList(List list){
		for(Object obj : list){
			System.out.println(obj.toString());
		}
	}

}
