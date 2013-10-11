package razvan.visma.test.business;

public class PriceCalculator {

	/**
	 * Calculates the price including the rebate
	 * 
	 * @param price
	 *            - the standard price
	 * @param quantity
	 *            - the number of products
	 * @param discount
	 *            - the discount in percent(%) to be applied
	 * */
	public static double calculatePrice(double price, int quantity,
			double discount) {
		double calculatedPrice = 0;

		calculatedPrice = price * quantity;

		if (discount > 0) {
			

			double calculatedDiscount = calculatedPrice *(discount/100);
			
			calculatedPrice = calculatedPrice - calculatedDiscount;

		}

		return calculatedPrice;
	}

}
