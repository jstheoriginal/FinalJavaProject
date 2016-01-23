package craftyCanadian;

import java.io.*;

public class WebsiteItem implements Serializable {

	/** Instance variables */
	private String province;
	private String date;
	private String address;
	private String craftName;
	private String tablePrice;
	private String description;
	private static final long serialVersionUID = 6529685098267757690L;
	
	/**
	 * Constructor
	 */
	public WebsiteItem(String province, String date, String address, String craftName,
			String tablePrice, String description) {
		super();
		this.province = province;
		this.date = date;
		this.address = address;
		this.craftName = craftName;
		this.tablePrice = tablePrice;
		this.description = description;
		
	}

	
	/** Getters/Setters */
	
	public String getProvince() {
		return province;
	}

	public String getDate() {
		return date;
	}

	public String getAddress() {
		return address;
	}

	public String getCraftName() {
		return craftName;
	}

	public String getTablePrice() {
		return tablePrice;
	}

	public String getDescription() {
		return description;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCraftName(String craftName) {
		this.craftName = craftName;
	}

	public void setTablePrice(String tablePrice) {
		this.tablePrice = tablePrice;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
