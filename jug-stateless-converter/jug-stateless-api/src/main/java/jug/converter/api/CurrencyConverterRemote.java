package jug.converter.api;

import java.math.BigDecimal;
import javax.ejb.Remote;



/**
 * Example of a Remote Business Interface
 * */
@Remote
public interface CurrencyConverterRemote {
	
	public static final String BEAN_NAME = "CurrencyConverterSession";

	BigDecimal convertEuroToRon(BigDecimal euro);

	BigDecimal converRonToEuro(BigDecimal ron);

}
