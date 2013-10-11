package razvan.visma.test.dao;

import java.util.ArrayList;
import java.util.List;

import razvan.visma.test.model.Product;
import razvan.visma.test.model.ProductRebate;

public class MockupProductDAO implements ProductDAO {

	private static List<Product> products;

	public MockupProductDAO() {
		if (products == null) {
			products = new ArrayList<Product>();
			products.add(createProduct("Chair", 20));
			products.add(createProduct("Desk", 30));
			Product bookCase = createProduct("BookCase", 40);
			bookCase.setRebate( new ProductRebate(10));
			products.add(bookCase);
			products.add(createProduct("RollBox", 10));
		}
	}

	private Product createProduct(String name, double price) {
		Product product = new Product(name, price);
		return product;
	}

	@Override
	public List<Product> getProducts() {

		return products;
	}

}
