package razvan.visma.test.model;

import java.util.Map;

public class Customer {

	private String name;

	private Rebate rebate;

	// Product, QuantityPurchased
	private Map<Product, Integer> purchaseHistory;

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rebate getRebate() {
		return rebate;
	}

	public void setRebate(Rebate rebate) {
		this.rebate = rebate;
	}

	public Map<Product, Integer> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(Map<Product, Integer> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}


	
	

}
