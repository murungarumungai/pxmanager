package razvan.visma.test.model;

public abstract class Rebate {



	/**
	 * Based on the Type of Rebate, you return an implementation:
	 * <p>
	 * Eg:<br/>
	 * - How Much The Customer Sales/Volume discount<br/>
	 * - special product offer/special deals<br/>
	 * - time of the year<br/>
	 * 
	 * so each Concrete Type of Rebate must implement this method and return the
	 * Discount in % which will be applied to the Standard product price.
	 * </p>
	 * 
	 * @return the percent % of discount
	 * */
	public abstract double getDiscount();

	
	


}
