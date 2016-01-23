package craftyCanadian;

import java.io.*;
import java.util.*;

public class Ad implements Serializable, Comparator<Ad> {

	/** Instance variables */
	private String websiteName;
	private String url;
	private static final long serialVersionUID = 6529685098267757690L;
	
	/**
	 * Constructor
	 * 
	 * @param url the url to the website the ad is on
	 * @param websiteName the name of the website the ad is on
	 */
	public Ad(String websiteName, String url) {
		
		super();
		this.websiteName = websiteName;
		this.url = url;
	
	}
	
	/** Getters/Setters */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getWebsiteName() {
		return websiteName;
	}
	
	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}
	
	/**
	 * Overridden toString() method to return more detailed information about the object.
	 */
	public String toString() {
		
		return this.websiteName + " (" + this.url + ")";
	}

	/**
	 * Overridden compare() method to compare ads by website name.
	 */
	public int compare(Ad o1, Ad o2) {
		
		return o1.getWebsiteName().compareTo(o2.getWebsiteName());
	}

}
