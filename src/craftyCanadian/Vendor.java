package craftyCanadian;

import java.io.*;
public class Vendor implements Serializable, Comparable<Vendor> {

	private String vendorName; //the vendor's name
	private String[] craftType; //a list of craft types for the vendor
	
	private static final long serialVersionUID = 5583838200300928382L;
	
	/** Constructor
	 * 
	 * @param vendorName the name of the vendor
	 * @param craftTypes the crafts for the vendor
	 */
	Vendor(String vendorName, String[] craftType) {
		super();
		this.vendorName = vendorName;
		this.craftType = craftType;
	}

	/** Getters/Setters */
	public String getVendorName() {
		return vendorName;
	}
	
	public String[] getCraftType() {
		return craftType;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public void setCraftType(String[] craftType) {
		this.craftType = craftType;
	}

	/**
	 * Overridden toString() method to return more detailed information about the object.
	 */
	public String toString() {
		
		return this.vendorName;
	}
	
	/**
	 * Compare vendors by name by default.
	 */
	@Override
	public int compareTo(Vendor o) {
		
		return getVendorName().compareTo(o.getVendorName());
	}
	
}