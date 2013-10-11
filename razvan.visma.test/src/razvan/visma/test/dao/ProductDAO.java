package razvan.visma.test.dao;

import java.util.List;

import razvan.visma.test.model.Product;

public interface ProductDAO {
	
	/**
	 * Returns all the Products
	 * */
	List<Product> getProducts();

}
