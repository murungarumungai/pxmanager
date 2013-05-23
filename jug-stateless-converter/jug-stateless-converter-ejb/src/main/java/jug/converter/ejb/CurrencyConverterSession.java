package jug.converter.ejb;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;


import jug.converter.api.CurrencyConverterRemote;



@Stateless
public class CurrencyConverterSession implements CurrencyConverterRemote,
		CurrencyConverterLocal {

	private BigDecimal euroRate;
	private BigDecimal ronRate;

	/**
	 * <p>
	 * As part of the component-container relationship, the container manages
	 * the life cycle of a session bean. Understanding the life cycle and the
	 * transitions of a session bean will enable you to create more robust and
	 * complete solutions. As a session bean goes through these life cycle
	 * transitions, the session bean can receive notification from the container
	 * in the form of callback or life cycle event handlers.
	 * </p>
	 * <p>
	 * When a session bean transitions from the “instantiation” state to the
	 * “ready” state, it transitions through the @PostConstruct phase. The @PostConstruct
	 * phase provides secondary, potentially complex bean initialization. To
	 * facilitate this secondary initialization, the container will invoke a
	 * method described with the @PostConstruct annotation. This @PostConstruct
	 * method functions as a life cycle callback. The @PostConstruct life cycle
	 * callback is invoked after the container performs its container-specific
	 * initialization of the bean and before the container executes any business
	 * methods. Container initialization typically includes injecting resources
	 * required for the bean to be a valid object, like a reference to the
	 * EJBContext.
	 * </p>
	 * */
	@PostConstruct
	public void getRates() {
		//initialize exchange rates
		euroRate = new BigDecimal("0.23");
		ronRate = new BigDecimal("4.33");
	}

	public BigDecimal convertEuroToRon(BigDecimal euro) {
		BigDecimal result = euro.multiply(ronRate);
		return result.setScale(2, BigDecimal.ROUND_UP);
	}

	public BigDecimal converRonToEuro(BigDecimal ron) {
		BigDecimal result = ron.multiply(euroRate);
		return result.setScale(2, BigDecimal.ROUND_UP);
	}

	public void addCurrency(String currency) {
		throw new UnsupportedOperationException(
				"Not implemented yet. Method is just an example for pointing out the technology features");
	}

}
