package razvan.visma.test.model;

public class ProductRebate extends Rebate {

	private double percetangeDiscount;

	/**
	 * Product Discount. A product can have special product offers rebate A
	 * product can have a special discount.
	 * 
	 * In this case a SpecialProductRebate belongs to a product
	 * */

	public ProductRebate(/* Product product, */double percetangeDiscount) {
		// this.product = product;
		this.percetangeDiscount = percetangeDiscount;
	}

	@Override
	public double getDiscount() {

		return percetangeDiscount;
	}

	@Override
	public String toString() {
		String result = ProductRebate.class.getSimpleName();

		return result;
	}

}
