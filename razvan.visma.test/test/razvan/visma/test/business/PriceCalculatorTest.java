package razvan.visma.test.business;

import static org.junit.Assert.*;

import org.junit.Test;

public class PriceCalculatorTest {
	
	private static final double DELTA = 1e-15;

	@Test
	public void testCalculatePriceNoDiscount() {
		double expectedPrice = 10;
		
		double result = PriceCalculator.calculatePrice(10, 1, 0);
		
		assertEquals(expectedPrice, result, DELTA);
	}
	
	@Test
	public void testCalculatePriceWithDiscount() {
		double expectedPrice = 90;
		
		double result = PriceCalculator.calculatePrice(100, 1, 10);
		
		assertEquals(expectedPrice, result, DELTA);
	}
	
	@Test
	public void testCalculatePriceWithSameNumberOfDigits() {
		double expectedPrice = 17;
		
		double result = PriceCalculator.calculatePrice(20, 1, 15);
		
		assertEquals(expectedPrice, result, DELTA);
	}

}
