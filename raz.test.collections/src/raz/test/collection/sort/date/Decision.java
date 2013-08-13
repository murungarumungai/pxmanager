package raz.test.collection.sort.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Aici vreau sa testez sortarea unei Liste de Obiecte Decision dupa Date
 * */
public class Decision {

	static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy.MM.dd");

	private Date decisionDate;
	
	public Decision(){
		decisionDate = null;
	}

	/**
	 * aaaa/ll/zz
	 * */
	public Decision(String date) {

		try {
			decisionDate = SDF.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public Date getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(Date decisionDate) {
		this.decisionDate = decisionDate;
	}
	
	@Override
	public String toString(){
		String result = super.toString();
		String date = decisionDate!=null ? "["+SDF.format(decisionDate)+"]" : "[null]";
		
		return result+date;
	}

}
