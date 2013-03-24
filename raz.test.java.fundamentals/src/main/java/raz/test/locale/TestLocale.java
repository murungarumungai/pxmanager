package raz.test.locale;

import java.util.Locale;

public class TestLocale {

	public static void main(String[] args) {
		System.out.println("Test Locale");

		// get the default locale
		Locale l = Locale.getDefault();
		System.out.println("   Language, Country, Variant, Name");
		System.out.println("");
		System.out.println("Default locale: ");
		System.out.println("   " + l.getLanguage() + ", " + l.getCountry()
				+ ", " + ", " + l.getVariant() + ", " + l.getDisplayName());
	}

}
