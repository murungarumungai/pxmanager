package at.apa.pdfwlserver.monitoring.data;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * represents a mutation from .csv
 * */

public class Mutation {

	private String name;
	private Date dataProcessed;
	private Date dataDueDate;
	private Date dataEarliestDelivery;

	public Mutation(String name, Date dataProcessed, Date dataDueDate,
			Date dataEarliestDelivery) {
		this.name = name;
		this.dataProcessed = dataProcessed;
		this.dataDueDate = dataDueDate;
		this.dataEarliestDelivery = dataEarliestDelivery;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDataProcessed() {
		return dataProcessed;
	}

	public void setDataProcessed(Date dataProcessed) {
		this.dataProcessed = dataProcessed;
	}

	public Date getDataEarliestDelivery() {
		return dataEarliestDelivery;
	}

	public void setDataEarliestDelivery(Date dataEarliestDelivery) {
		this.dataEarliestDelivery = dataEarliestDelivery;
	}

	public Date getDataDueDate() {
		return dataDueDate;
	}

	public void setDataDueDate(Date dataDueDate) {
		this.dataDueDate = dataDueDate;
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		//determine fields declared in this class only (no fields of superclass)
	    Field[] fields = this.getClass().getDeclaredFields();
	    for(Field field : fields){
	    	result.append("[");
	    	try{
	    		result.append(field.getName());
	    		result.append(":");
	    		//requires access to private field:
	            result.append( field.get(this) );
	    	}catch(IllegalAccessException ex){}
	    	result.append("]");
	    }
		return result.toString();
	}
	
	//TODO: override toString()
	//http://www.javapractices.com/topic/TopicAction.do?Id=55

}
