package razvan.visma.test.dao;

import java.util.List;

import razvan.visma.test.model.Customer;

public interface CustomerDAO {
	
	/**
	 * Returns all the Customers
	 * */
	List<Customer> getCustomers();

}
