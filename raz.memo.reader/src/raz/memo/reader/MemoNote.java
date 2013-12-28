package raz.memo.reader;

import java.text.ParseException;
import java.util.Date;

/**
 * This class represents a memo note and the transformations that need to be
 * done
 * */

public class MemoNote {

	static final String NOTE_ENTER = "=0D=0A";// this represents a 'enter'
												// character/carriage return

	static final String NOTE_BODY = "BODY;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:";
	static final String NOTE_DATE = "DCREATED:";
	static final String NOTE_LAST_MODIFIED = "LAST-MODIFIED:";
	static final String NOTE_END = "END:VNOTE";

	private final String rawInput;

	// private String content;//only the content of note

	private Date lastModified;

	/**
	 * Creates a note
	 * 
	 * @param input
	 *            - the rawInputString of a note
	 * */
	public MemoNote(String input) {
		rawInput = input;
	}

	// Metoda asta imi returneaza numai continutul notei, asa cum este afisat pe
	// ecran
	/**
	 * This method returns only the text content of a note, just as it would be
	 * displayed on the screen(e.g Line Brakes are preserved)
	 * */
	public String getContent() {
		String result = null;
		String temp = null;
		int beginIndex = rawInput.indexOf(NOTE_BODY);
		beginIndex = beginIndex + NOTE_BODY.length();

		int endIndex = rawInput.indexOf(NOTE_DATE);

		if (beginIndex > 1 && endIndex > beginIndex) {
			temp = rawInput.substring(beginIndex, endIndex);
		}

		if (null != temp) {
			String eol = System.getProperty("line.separator");
			result = temp.replace(NOTE_ENTER, eol);
		}

		return result;
	}

	/**
	 * Parses the Note Content and returns only the String representing the
	 * LastModified date
	 * */
	private String getLastModifiedContent() {
		String result = null;
		int beginIndex = rawInput.indexOf(NOTE_LAST_MODIFIED);
		beginIndex+=NOTE_LAST_MODIFIED.length();
		int endIndex = rawInput.indexOf(NOTE_END);
		result = rawInput.substring(beginIndex, endIndex);
		return result;
	}

	/**
	 * Returns the Last Modified Date parsed from within the note metaData
	 * tags(e.g. LAST-MODIFIED: 20130406T103338) <br/>
	 * If No Date could be parsed from the note(or Date Parse Exception) it will
	 * return null. That is, no 'Last Modified Date' information is availble for
	 * this note
	 * */
	public Date getLastModified() {
		String sDate = getLastModifiedContent();
		if(sDate != null) {
			try {
				lastModified = DateUtil.parseMemoDate(sDate);
			} catch (ParseException e) {
				/* Nu are rost sa arunc exceptia Userului ca oricum nu poate face nimic
				 * Inseamna ca the Date inside the Note content is incorect
				 * */
				e.printStackTrace();
			}
		}
		return lastModified;
	}

}
