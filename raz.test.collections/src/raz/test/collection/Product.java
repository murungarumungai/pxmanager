package raz.test.collection;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private List<Source> sources;

	/**
	 * Constructorul default imi creaza un obiect cu 3 surse
	 * Sursa1,Sursa2,Sursa3
	 * */
	public Product() {
		sources = new ArrayList<Source>();
		sources.add(new Source("Sursa1"));
		sources.add(new Source("Sursa2"));
		sources.add(new Source("Sursa3"));
	}

	public List<Source> getSources() {
		return sources;
	}

	/**
	 * Prints the current sources
	 * */
	public void printSources() {
		System.out.println("sources.size():" + sources.size());
		if (null != sources && sources.size() > 0) {
			for (Source oneSource : sources) {
				System.out.println(oneSource);
			}
		} else {
			System.out.println("Null or Empty list");
		}
	}

}
