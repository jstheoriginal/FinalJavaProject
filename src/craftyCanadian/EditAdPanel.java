package craftyCanadian;

import java.awt.*;
import java.awt.event.*;
import java.net.*;

import javax.swing.*;


public class EditAdPanel extends JPanel implements ActionListener, FocusListener, 
											ItemListener {

	
	protected JLabel instructionsLabel, //instructions
					 websiteNameLabel, // website name label
	 				 websiteURLLabel; // the craft type label
	protected JTextField websiteNameField, // the field for the website name
		 				 websiteURLField; // the field for entering the website URL
	protected JButton editButton, // button to add the ads
					  updateButton; // button to update the ads
	protected JPanel comboPanel, //panel for the combo box
					 formPanel, // the form for adding ad panels
	 				 buttonPanel; // the button panel
	private final int ROWS = 12; // number of rows for layout
	private static JComboBox<Ad> combo = new JComboBox<Ad>(); //combo box to hold the editable ads
	private int index = -1; // index of combo box

	/**
	* Constructor
	*/
	public EditAdPanel() {	
		
		//Create and populate the panel with 10px gaps.
	    setLayout(new BorderLayout(10, 10));
	    
		//Build the Form panel
	    buildFormPanel();
	    
	    //Build the Button panel
	    buildButtonPanel();
	    
        //Build the combo panel
        buildComboPanel();
	    
	    //Add the form & button panels
	    add(comboPanel, BorderLayout.NORTH);
	    add(formPanel, BorderLayout.CENTER);
	    add(buttonPanel, BorderLayout.SOUTH);       
		
		//Add a border around the panel.
//	    setBorder(BorderFactory.createTitledBorder("Edit Website Ad"));
	    
		// add action listener to the combo box
		combo.addItemListener(this);

	}
	
	private void buildComboPanel() {
		
		comboPanel = new JPanel(new BorderLayout(10, 10));
		
		//set text for instructions.
		instructionsLabel = new JLabel("<html><BR>Choose an ad from the drop-down box below to show " +
								"its current attributes. <BR>Change any values required and click " +
								"the 'Edit Venue' button to apply changes.<BR></html>");
		
	    //fill combo box
		refreshComboBox();
		
		// add components
		comboPanel.add(instructionsLabel, BorderLayout.NORTH);
		comboPanel.add(combo, BorderLayout.SOUTH);
		
		// add action listener to the combo box
		combo.addItemListener(this);
	}
	
	
	private void buildFormPanel() {
		
		//Create form panel with SpringLayout
		formPanel = new JPanel(new SpringLayout());
        
		//Create the input labels
		websiteNameLabel = new JLabel("Website Name:", JLabel.TRAILING);
		websiteURLLabel = new JLabel("Website URL:", JLabel.TRAILING);
		
		//Create the input fields with 20-characters
		websiteNameField = new JTextField(20);
		websiteURLField = new JTextField(20);
		
		//Set labels for fields
		websiteNameLabel.setLabelFor(websiteNameField);
		websiteURLLabel.setLabelFor(websiteURLField);

		//Add components to panel
		formPanel.add(websiteNameLabel);	formPanel.add(websiteNameField);
		formPanel.add(websiteURLLabel);		formPanel.add(websiteURLField);
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		
        //Lay out the panel
        SpringUtilities.makeCompactGrid(formPanel,
                                        ROWS, 2, 		 //rows, cols
                                        6, 6,        //initX, initY
                                        6, 6);       //xPad, yPad
        
		// Add focusListeners to the  textfields
		websiteNameField.addFocusListener(this);
		websiteURLField.addFocusListener(this);
	
	}
	
	/**
	 * The buildButtonPanel method creates two buttons.
	 */
	private void buildButtonPanel() {
		
		//Create button panel
		buttonPanel = new JPanel();
		
		//Set the layout to flow
		buttonPanel.setLayout(new FlowLayout());
		
		//Create the two JButtons and add them to the panel
		updateButton = new JButton("Refresh Ad List");
		editButton = new JButton("Edit Website Ad");
		buttonPanel.add(updateButton);
		buttonPanel.add(editButton);
		
		//Add action listeners to the two buttons
		updateButton.addActionListener(this);
		editButton.addActionListener(this);
	}
	
	/**
	* The getAdValues method calls a two-arg constructor of the Ad
	* class passing the contents of the Ad panel textfields.
	* 
	* @return The bookInventory object that was created.
	*/
	public Ad getAdValues() {
	   Ad ad = new Ad(
		   websiteNameField.getText(),
		   websiteURLField.getText()
		   );
	   
	   return ad;
	}
	   
	/**
    * The clearValues method clears the values in the input fields.
    */
	public void clearValues() {
		websiteNameField.setText("");
		websiteURLField.setText("http://");
	}
	
	/** 
	 * The refreshComboBox method gets the ad list from the file and 
	 * recreates the combo box with the most current list of ads.
	 */
	public static void refreshComboBox() {
		
		//refresh combo box contents
		if(combo.getItemCount() > 0)
			combo.removeAllItems();
		
		for(Ad ad : FileHandler.adsList) {
	    	combo.addItem(ad);
	    }
		
		//select nothing
		combo.setSelectedIndex(-1);
	}
	
	/**
	 * The focusGained events for the event listener
	 */
	public void focusGained(FocusEvent fe) {
		
	//	refreshComboBox();
		
		// When the user tabs into the website name textfield
		if (fe.getSource() == websiteNameField) {
			// Select the contents of the field
			websiteNameField.selectAll();
		}
		
		// When the user tabs into the website URL textfield
		if (fe.getSource() == websiteURLField) {
			// Select the contents of the field
			websiteURLField.selectAll();
		}
	}

	/**
	 * The focusLost events for the event handler
	 */
	public void focusLost(FocusEvent fe) {
		
		// Validate that the URL field is a valid URL when user tabs off the textfield
		// Only check that the URL starts with http:// or https://
		if (fe.getSource() == websiteURLField && 
			websiteURLField.getText().length() != 0) {
		
			boolean isValidURL;
			
			if(isUrl(websiteURLField.getText()))
				isValidURL = true;
			else
				isValidURL = false;
			
			// tell the user that the URL is invalid if isValidURL is false
			if(isValidURL == false)
				JOptionPane.showMessageDialog(this, 
						"This is not a valid website URL.");
		}
		
		
	} // end focusLost
	
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		
		// check if combo box item is selected
		if(ie.getStateChange() == ItemEvent.SELECTED) {
			
			if(combo.getSelectedIndex() == -1) {
				clearValues();
			} else {
				index = combo.getSelectedIndex();

				displayValues();
			}

		}
		
	}
	
	/**
	 * The actionPerformed events for the event listener
	 */
	public void actionPerformed(ActionEvent ae) {
		
		// When the edit Button is clicked		
		if (ae.getSource() == editButton) {
			
			if(combo.getSelectedIndex() > -1) {
				FileHandler.adsList.get(index).setWebsiteName(websiteNameField.getText());
				FileHandler.adsList.get(index).setUrl(websiteURLField.getText());
				
				// write the updated venues info to file
				FileHandler.writeAdsFile();
				
				// let user know successfully added and to refresh data on left
				JOptionPane.showMessageDialog(this, "Ad successfully edited.");
				
				//refresh combo box contents
				refreshComboBox();
				
				//Clear values of form
				clearValues();
				
				//Update View Panel's table
				ViewAdPanel.updateTable();
			}
			
		} 
		
		// When update Button is clicked
		if (ae.getSource() == updateButton) {
			
			refreshComboBox();
		}
	} 

	/**
	 * The displayValues() method gets the values for the currently-selected venue
	 */
	public void displayValues() {

		if(combo.getSelectedIndex() != -1) {
			websiteNameField.setText(FileHandler.adsList.get(index).getWebsiteName());
			websiteURLField.setText(FileHandler.adsList.get(index).getUrl());
		}
	}
	
	
	/**
	 * The isUrl method check whether a string is a valid URL.
	 * @param text a string URL
	 * @return a boolean describing the validity of the url
	 */
	public static boolean isUrl(String text){
		
		try{
			URL url = new URL(text);
			url.getPath();
			
		} catch (MalformedURLException e) {
			
			// if exception thrown, not valid
			return false;
		}
		return true;
	}
}
