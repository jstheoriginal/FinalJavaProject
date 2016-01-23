package craftyCanadian;

import java.io.*;
import java.text.*;
import java.util.*;

public class CraftSale implements Serializable, Comparable<CraftSale> {

	private Date date;
	private String craftSaleName;
	private double tablePrice;
	private String description;
	private String theme;
	private String startTime;
	private String endTime;
	private String rules;
	private Venue venue;
	
	DateFormat df = new SimpleDateFormat("MMM d, yyyy");

	
	private static final long serialVersionUID = 6529685098102257690L;
	
	/** Constructor
	 * 
	 * @param date the date of the craft sale
	 * @param craftSaleName the name of the craft sale
	 * @param tablePrice the price of each table at the craft sale
	 * @param description the description of the craft sale
	 * @param theme the theme of the craft sale
	 * @param startTime the start time of the craft sale
	 * @param endTime the end time of the craft sale
	 * @param rules the rules of the craft sale
	 * @param venue the venue for the craft sale
	 */
	public CraftSale(String craftSaleName, Date date, String startTime, 
			String endTime, String theme, String description, String rules, 
			double tablePrice, Venue venue) {

		super();
		this.craftSaleName = craftSaleName;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.theme = theme;
		this.description = description;
		this.rules = rules;
		this.tablePrice = tablePrice;
		this.venue = venue;
	}

	/** Getters/Setters */
	
	public Date getDate() {
		return date;
	}

	public String getCraftSaleName() {
		return craftSaleName;
	}

	public double getTablePrice() {
		return tablePrice;
	}

	public String getDescription() {
		return description;
	}

	public String getTheme() {
		return theme;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getRules() {
		return rules;
	}
	
	public Venue getVenue() {
		return venue;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	public void setCraftSaleName(String craftSaleName) {
		this.craftSaleName = craftSaleName;
	}

	public void setTablePrice(double tablePrice) {
		this.tablePrice = tablePrice;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}
	
	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	
	/**
	 * Overridden toString() method to return more detailed information about the object.
	 */
	@Override
	public String toString() {
		
		return this.craftSaleName + " (" + df.format(this.date) + "; " + this.startTime 
				+ " at " + this.venue.getVenueName() + ")";
	}

	/**
	 * Sorts CraftSale objects by their craft sale name (default).
	 */
	@Override
	public int compareTo(CraftSale o) {
		
		return craftSaleName.compareTo(o.getCraftSaleName());
	}
	
}

/**
 * LocationSort sorts a List of CraftSale objects by their venue (by name).
 */
class LocationSort implements Comparator<CraftSale> {

	public int compare(CraftSale one, CraftSale two) {
		return one.getVenue().getVenueName().compareTo(two.getVenue().getVenueName());
	}
}

/**
 * DateAscSort sorts a List of Craft Sales objects by date, ascending
 */
class DateAscSort implements Comparator<CraftSale> {
	
	public int compare(CraftSale one, CraftSale two) {
		return one.getDate().compareTo(two.getDate());
	}
}

/**
 * DateDescSort sorts a List of Craft Sales objects by date, ascending
 */
class DateDescSort implements Comparator<CraftSale> {
	
	public int compare(CraftSale one, CraftSale two) {
		return two.getDate().compareTo(one.getDate());
	}
}
