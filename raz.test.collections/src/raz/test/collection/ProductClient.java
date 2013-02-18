package raz.test.collection;

import java.util.List;

public class ProductClient {
	
	
	public static void main(String[] args){
		Product p = new Product();
		p.printSources();
		
		List<Source> sources = p.getSources();
		sources.clear();
		sources.add(new Source("XX"));
		
		p.printSources();
	}

}
