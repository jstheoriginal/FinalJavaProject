package craftyCanadian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class AddAdPanel extends JPanel implements ActionListener, FocusListener {
	
	protected JLabel websiteNameLabel, // website name label
					 websiteURLLabel; // the craft type label
	protected JTextField websiteNameField, // the field for the website name
						 websiteURLField; // the field for entering the website URL
	protected JButton addButton, // button to add the vendor
					  clearButton; // button to clear the add ad input fields
	protected JPanel formPanel, // the form for adding vendor panels
					 buttonPanel; // the button panel
	protected final int ROWS = 14; // number of rows for layout
	
	/**
	 * Constructor
	 */
	public AddAdPanel() {

        //Create and populate the panel with 10px gaps.
        setLayout(new BorderLayout(10, 10));
        
		//Build the Form panel
        buildFormPanel();
        
        //Build the Button panel
        buildButtonPanel();
        
        //Add the form & button panels
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);       
		
		//Add a border around the panel.
//        setBorder(BorderFactory.createTitledBorder("Add Website Ad"));
	}
	
	private void buildFormPanel() {
		
		//Create form panel with SpringLayout
		formPanel = new JPanel(new SpringLayout());
        
		//Create the input labels
		websiteNameLabel = new JLabel("Website Name:", JLabel.TRAILING);
		websiteURLLabel = new JLabel("Website URL:", JLabel.TRAILING);
		
		//Create the vendor name & craft type input fields with 20-characters
		websiteNameField = new JTextField(20);
		websiteURLField = new JTextField("http://", 20);
		
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
		addButton = new JButton("Add Website Ad");
		clearButton = new JButton("Clear");
		buttonPanel.add(addButton);
		buttonPanel.add(clearButton);
		
		//Add action listeners to the two buttons
		addButton.addActionListener(this);
		clearButton.addActionListener(this);
	}
	
	/**
	* The getAdValues method calls a two-arg constructor of the Ad
	* class passing the contents of the Ad panel textfields.
	* 
	* @return The Ad object that was created.
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
	 * The focusGained events for the event listener
	 */
	public void focusGained(FocusEvent fe) {
		
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
	
	/**
	 * The actionPerformed events for the event listener
	 */
	public void actionPerformed(ActionEvent ae) {
		// When the Add Button is clicked
		if (ae.getSource() == addButton) {
			
			// add the venue to the venue list and write to file
			FileHandler.adsList.add(getAdValues());
			FileHandler.writeAdsFile();
			
			// let user know successfully added and to refresh data on left
			JOptionPane.showMessageDialog(this, "Ad successfully added.");
			
			//clear all of the values in the GUI textfields
			clearValues();
			
			//Update View Panel's table
			ViewAdPanel.updateTable();
			
			//Update Edit Panel's combo box
			EditAdPanel.refreshComboBox();
			
		} 
		
		// When the Clear button is clicked
		if (ae.getSource() == clearButton) {
			
			// clear all of the values in the GUI textfields
			clearValues();
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
