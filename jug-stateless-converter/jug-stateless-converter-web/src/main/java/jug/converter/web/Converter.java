package jug.converter.web;

import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jug.converter.ejb.CurrencyConverterLocal;



@Named
// The bean is given a name, so we can access it from JSF
@RequestScoped
// The bean is request scoped, a different greeting can be made in each request
public class Converter {


	//You can inject it(an EJB) when you use CDI in Java EE6, otherwise you can only inject it into other EJB's and servlets 
	@Inject
	CurrencyConverterLocal currencyConverter;

	private BigDecimal amountEur;
	private BigDecimal amountRon;

	public void convertEur() {
		setAmountRon(currencyConverter.convertEuroToRon(amountEur));
	}

	public BigDecimal getAmountRon() {
		return amountRon;
	}

	public void setAmountRon(BigDecimal amountRon) {
		this.amountRon = amountRon;
	}

	public BigDecimal getAmountEur() {
		return amountEur;
	}

	public void setAmountEur(BigDecimal amountEur) {
		this.amountEur = amountEur;
	}





}
