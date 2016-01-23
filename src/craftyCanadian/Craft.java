package craftyCanadian;

import java.io.Serializable;

public class Craft implements Serializable {

	/** Instance variables */
	private String craftName;
	private static final long serialVersionUID = 7889685098267757690L;

	/**
	 * Constructor
	 * 
	 * @param craftName the name of the craft
	 */
	public Craft(String craftName) {
		super();
		this.craftName = craftName;
	}

	/** Getters/Setters */
	
	public String getCraftName() {
		return craftName;
	}

	public void setCraftName(String craftName) {
		this.craftName = craftName;
	}
	
}
