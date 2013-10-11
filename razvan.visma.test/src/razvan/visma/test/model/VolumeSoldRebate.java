package razvan.visma.test.model;

import java.util.Map;

public class VolumeSoldRebate extends Rebate {

	private final Customer customer;

	private int unitsThreshold;

	private double discount;

	/**
	 * Instantiate a new VolumeSoldRebate.
	 * 
	 * A Customer has a VolumeSoldRebate
	 * 
	 * a VolumeSoldRebate belongs to a Customer,
	 * 
	 * @param customer
	 *            - {@link Customer}
	 * */
	public VolumeSoldRebate(Customer customer, int unitsThreshold,
			double discount) {
		this.customer = customer;
		this.setUnitsThreshold(unitsThreshold);
		this.setDiscount(discount);
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public double getDiscount() {
		//TODO:
		// double discount = 0;
		// If this customer sold more than 100 products,
		// give him 10%(the discont) off, othervise return 0%
		 Map<Product, Integer> productHistory = getCustomer().getPurchaseHistory();
		
		

		return discount;
	}

	public int getUnitsThreshold() {
		return unitsThreshold;
	}

	public void setUnitsThreshold(int unitsThreshold) {
		this.unitsThreshold = unitsThreshold;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		String result = VolumeSoldRebate.class.getSimpleName();
		if (unitsThreshold > 0) {
			result = result + "( > " + unitsThreshold + " Units)";
		}
		return result;
	}

}
