package raz.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Google Python Class Day 2 Part 1
/**
 * <a href="http://docs.oracle.com/javase/tutorial/essential/regex/index.html">Lesson: Regular Expressions</a>
 * 
 * */

public class RegEx {

	// static String regEx ;
	static Pattern pattern;
	static Matcher matcher;

	// static String input;

	public static void main(String[] args) {
		
		//exercisesFromGooglePythonRegEx();
		exerciseFromJavaTutorial();
	}
	
	public static void exerciseFromJavaTutorial(){
		
		/**
		 * Character classes
		 * */
		//'[abc]at' - inseamna a sau b sau c, nu conteaza ordinea
		simple("batcatrat","[abc]at");
		
		/**
		 * Negation
		 * 
		 * match all the characters EXCEPT those negated
		 * */
		simple("bat cat rat fuck", "\\w+[\\w+&&[^a]]");
		
		
	}
	
	public static void exercisesFromGooglePythonRegEx(){
				// Rule 1: simple characters match themselves
				simple("called Piiig", "iig");
				simple("foofoofoo", "foo");
				// Metacharacters
				/**
				 * The match still succeeds, even though the dot "." is not present in
				 * the input string. It succeeds because the dot is a metacharacter — a
				 * character with special meaning interpreted by the matcher. The
				 * metacharacter "." means "any character" which is why the match
				 * succeeds in this example.
				 * */
				simple("cats", "cat.");
				simple("piiig","...g");//RegEx reads: .(AnyChar).(AnyChar).(AnyChar)g
				
				simple("ca.lled","\\.ll");//Daca vrei sa cauti dot"." you must escape it in RegEx language by "\."
				
				//The ""word character" '\w'
				simple("blah :cat blah blah", ":\\w\\w\\w");//cauta ':' urmat de 3 word characters
				
				//Looking for digits '\d'
				simple("blah :123xxx", "\\d\\d\\d");//cauta 3 digits in a row
				
				//White space '\s'
				simple("blah :1 2 3xxx", "\\d\\s\\d\\s\\d");//cauta 3 digits in a row with white space
				
				//One or more
				/**
				 * '+' to the right of something means 1 or more
				 * '*' to the right of something means 0 or more
				 * */
				// '\s+'
				simple("blah :1         2   3xxx", "\\d\\s+\\d\\s+\\d");//cauta 3 digits in a row with one or more white space '\s'
				
				simple("blah blah :kitten blah blah", ":\\w+");// search for colon ':'  and then there are some number of word characters. Space is not a word character and it will stop

				
				
				simple("blah blah :kitten123 blah  ^ & [ blah", ":.+");// search for colon ':' follows by '.+' -  one or more of any character
				
				
				//NON-Whitespace '\S' - ia totul ce nu este WhitSpace in considerare 
				simple("blah blah :kitten123&a=* blah blah", ":\\S+");// ':\S+' search for colon ':' follows by '\S+' -  one or more of any NON-Whitespace Characters
				
				//one or more word chars '\w+', followed by '@', followed by one or more word chars '\w+', followed by dot '\.', followed by one or more word chars '\w+'
				//Asta nu merge pt ca dot '.' - is not a word character
				simple("blah blah nick.p@gmail.com yattaa @ blahh", "\\w+@\\w+\\.\\w+");
				
				//To specify a set of characters use square brackets '['
				
				/**
				 * In the context of regular expressions, a character class is a set of characters enclosed within square brackets. 
				 * It specifies the characters that will successfully match a single character from a given input string.
				 * */
				simple("blah blah nick.p@gmail.com yattaa @ blahh", "[\\w.]+@\\w+\\.\\w+");//because the '.' it is in '[' it is not a meta-character
				// '[\w.]+' any number of characters from character class(set) '[\w.]'
				//@ followed by '@'
				//'\w+' followed by one or more of word characters
				//'.' followed by dot '.'
				//'\w+' followed by one or more of word characters
				
				//Sau scrisa mai simplu '[\w.]+@[\w.]+'
				simple("blah blah .nick.p@gmail.com yattaa @ blahh", "[\\w.]+@[\\w.]+");//Once inside the brackets '[]'the order doesn't matter
				
				//Problema e ca imi returneaza '.nick.p@gmail.com' . To fix that:
				simple("blah blah .nick.p@gmail.com yattaa @ blahh yatta foo@bar", "\\w[\\w.]*@[\\w.]+");
				//'\w[\w.]*' = incepe cu a word character si apoi continua cu '[\w.]*' zero sau mai multe charactere din setul '[\w.]'
				
				/*
				 * Acum, daca vreau ca din expresia regulata sa imi extrag grupuri folosesc '()'
				 * */
				simplePrintSeparateGroups("blah blah nick.p@gmail.com yattaa @ blahh", "([\\w.]+)@([\\w.]+)");
	}

	public static void simple(String input, String regEx) {
		System.out.println(regEx);
		pattern = Pattern.compile(regEx);
		matcher = pattern.matcher(input);

		boolean found = false;
		// find the next subSequence of the input sequence that matches the
		// pattern
		while (matcher.find()) {
			String matchedSubsequence = matcher.group();
			int start = matcher.start();
			int end = matcher.end();

			System.out.println("I have found the text '" + matchedSubsequence
					+ "' starting at index:" + start + " ending at index:"
					+ end);

			found = true;
		}

		if (!found) {
			System.out.println("No match found!");
		}
	}
	
	public static void simplePrintSeparateGroups(String input, String regEx) {

		pattern = Pattern.compile(regEx);
		matcher = pattern.matcher(input);

		boolean found = false;
		// find the next subSequence of the input sequence that matches the
		// pattern
		while (matcher.find()) {
			int groupCount = matcher.groupCount();
			for(int i=1; i<=groupCount; i++){//group count 2. group1 si group2
				String matchedSubsequenceGroup = matcher.group(i);
				int start = matcher.start();
				int end = matcher.end();
				System.out.println("I have found the text, displaying group["+i+"] '" + matchedSubsequenceGroup+ "' starting at index:" + start + " ending at index:"+ end);
			}
			found = true;
		}

		if (!found) {
			System.out.println("No match found!");
		}
	}

}
