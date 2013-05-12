package jug.converter.ejb;

import javax.ejb.Local;

import jug.converter.api.CurrencyConverterRemote;

@Local
public interface CurrencyConverterLocal extends CurrencyConverterRemote {
	
	/**
	 * a local client could have access an extended set of methods of the same Session Bean
	 * which exposes a Remote Business Interface
	 * */
	void addCurrency(String currency);

}
