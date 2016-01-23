package craftyCanadian;

import java.io.Serializable;

public class Venue implements Serializable, Comparable<Venue> {

	private enum Province {AB, BC, MB, NB, NL, NS, NT, NU, ON, PE, QC, SK, YT}
	private Province province;
	private String venueName;
	private String street;
	private String city;
	private String venueType;
	private int tableCount; //not sure if this is for the location or craft sale
	private static final long serialVersionUID = 6529685098267752230L;
	
	/** 
	 * Constructor
	 */
	public Venue(String venueName, String street, String city, 
			String province, String venueType, int tableCount) {
		super();
		this.venueName = venueName;
		this.street = street;
		this.city = city;
		this.province = Province.valueOf(province);
		this.venueType = venueType;
		this.tableCount = tableCount;
	}

	/** Getters/Setters */
	
	public String getVenueName() {
		return venueName;
	}

	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getProvince() {
		return province.toString();
	}
	
	public String getVenueType() {
		return venueType;
	}

	public int getTableCount() {
		return tableCount;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setProvince(String province) {
		this.province = Province.valueOf(province);
	}
	
	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}

	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;
	}
	
	/**
	 * Overridden toString() method to return more detailed information about the object.
	 */
	public String toString() {
		
		return this.venueName + " (" + this.city + ", " + this.province + ")";
	}

	/**
	 * Overridden compareTo(Venue) method compares Venues by venue name by default.
	 */
	@Override
	public int compareTo(Venue o) {
		
		return venueName.compareTo(o.getVenueName());
	}
}
