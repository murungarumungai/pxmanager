package razvan.visma.test.dao;

import java.util.ArrayList;
import java.util.List;

import razvan.visma.test.model.Customer;
import razvan.visma.test.model.Rebate;
import razvan.visma.test.model.VolumeSoldRebate;

public class MockupCustomerDAO implements CustomerDAO {

	private static List<Customer> customers;

	public MockupCustomerDAO() {
		if (customers == null) {
			customers = new ArrayList<Customer>();

			Customer custWithRebate = createCustomer("Customer1");
			Rebate rebate = new VolumeSoldRebate(custWithRebate, 100, 15);
			custWithRebate.setRebate(rebate);
			customers.add(custWithRebate);
		
			customers.add(new Customer("Customer2"));
		}
	}

	private Customer createCustomer(String name) {
		Customer customer = new Customer(name);
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {

		return customers;
	}

}
