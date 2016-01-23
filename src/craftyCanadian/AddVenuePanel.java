package craftyCanadian;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AddVenuePanel extends JPanel implements ActionListener, FocusListener {
	
	protected JLabel venueNameLabel, // venue name label
					 streetLabel, // the venue's street address label
					 cityLabel, // the venue's city label
					 provinceLabel, // venue's province label
					 typeLabel, // the type of venue label
					 tableCapacityLabel; // the number of tables the venue holds label
	protected JTextField venueNameField, // the field for the venue name
						 streetField, // the field for entering the venue's street address
						 cityField, // the venue's city field
						 provinceField, // venue's province field
						 typeField, // the type of venue field
						 tableCapacityField; // the number of tables the venue holds field
	protected enum Province {AB, BC, MB, NB, NL, NS, NT, NU, ON, PE, QC, SK, YT} // valid provinces
	protected JComboBox<Province> provinceCombo; // combo box for selecting province
	protected JButton addButton, // button to add the venue
					  clearButton; // button to clear the add venue input fields
	protected JPanel formPanel, // the form for adding
					 buttonPanel; // the button panel
	protected final int ROWS = 9; // number of rows for layout
	
	/**
	 * Constructor
	 */
	public AddVenuePanel() {
		
        //Create and populate the panel with 10px gaps.
        setLayout(new BorderLayout(10, 10));
        
		//Build the Form panel
        buildFormPanel();
        
        //Build the Button panel
        buildButtonPanel();
        
        //Add the form & button panels
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);       

	}
	
	private void buildFormPanel() {
		
		//Create form panel with SpringLayout
		formPanel = new JPanel(new SpringLayout());
        
		//Create the venue name & craft type labels
		venueNameLabel = new JLabel("Venue Name:", JLabel.TRAILING);
		streetLabel = new JLabel("Street Address:", JLabel.TRAILING);
		cityLabel = new JLabel("City/Town:", JLabel.TRAILING);
		provinceLabel = new JLabel("Province:", JLabel.TRAILING);
		typeLabel = new JLabel("Venue Type:", JLabel.TRAILING);
		tableCapacityLabel = new JLabel("Table Capacity:", JLabel.TRAILING);
		
		//Create the venue name & craft type input fields with 20-characters
		venueNameField = new JTextField(20);
		streetField = new JTextField(20);
		cityField = new JTextField(20);
		typeField = new JTextField(20);
		tableCapacityField = new JTextField("0", 6);
		
		//Create province combo box pre-filled with valid provinces
		provinceCombo = new JComboBox<Province>(Province.values());
		
		//Set provinceCombo to show blank initially
		provinceCombo.setSelectedIndex(-1);
		
		//Set labels for fields
		venueNameLabel.setLabelFor(venueNameField);
		streetLabel.setLabelFor(streetField);
		cityLabel.setLabelFor(cityLabel);
		provinceLabel.setLabelFor(provinceCombo);
		typeLabel.setLabelFor(typeField);
		tableCapacityLabel.setLabelFor(tableCapacityField);

		//Add components to panel
		formPanel.add(venueNameLabel);		formPanel.add(venueNameField);
		formPanel.add(streetLabel);			formPanel.add(streetField);
		formPanel.add(cityLabel);			formPanel.add(cityField);
		formPanel.add(provinceLabel);		formPanel.add(provinceCombo);
		formPanel.add(typeLabel);			formPanel.add(typeField);
		formPanel.add(tableCapacityLabel);	formPanel.add(tableCapacityField);
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		
        //Lay out the panel
        SpringUtilities.makeCompactGrid(formPanel,
                                        ROWS, 2,     //rows, cols
                                        6, 6,        //initX, initY
                                        6, 6);       //xPad, yPad
        
        // Add focusListeners to the text fields
        venueNameField.addFocusListener(this);
		streetField.addFocusListener(this);
		cityField.addFocusListener(this);
		typeField.addFocusListener(this);
		tableCapacityField.addFocusListener(this);
		
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
		addButton = new JButton("Add Venue");
		clearButton = new JButton("Clear");
		buttonPanel.add(addButton);
		buttonPanel.add(clearButton);
		
		//Add action listeners to the two buttons
		addButton.addActionListener(this);
		clearButton.addActionListener(this);
	}
	
	/**
	* The getVenueValues method calls a 6-arg constructor of the Venue
	* class passing the contents of the Venue panel textfields.
	* 
	* @return The Venue object that was created.
	*/
	public Venue getVenueValues() {
	   Venue venue = new Venue(
		   venueNameField.getText(),
		   streetField.getText(),
		   cityField.getText(),
		   provinceCombo.getSelectedItem().toString(),
		   typeField.getText(),
		   Integer.parseInt(tableCapacityField.getText())
		   );
	   
	   return venue;
	}
	   
	/**
    * The clearValues method clears the values in the input fields.
    */
	public void clearValues() {
		venueNameField.setText("");
		streetField.setText("");
		cityField.setText("");
		provinceCombo.setSelectedIndex(-1);
		typeField.setText("");
		tableCapacityField.setText("");
	}
	
	/**
	 * The focusGained events for the event listener
	 */
	public void focusGained(FocusEvent fe) {
		
		// When the user tabs into the table capapcity textfield
		if (fe.getSource() == tableCapacityField) {
			
			// Select the contents of the field
			tableCapacityField.selectAll();
		}

	}

	/**
	 * The focusLost events for the event handler
	 */
	public void focusLost(FocusEvent fe) {
		
		// when user exits the table capacity field, check whether a valid number
		if (fe.getSource() == tableCapacityField) {
			
			try {
				Double.parseDouble(tableCapacityField.getText());
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "Please enter a valid number.");
			}
		}
		
	} // end focusLost
	
	/**
	 * The actionPerformed events for the event listener
	 */
	public void actionPerformed(ActionEvent ae) {
		
		// When the Add Button is clicked
		if (ae.getSource() == addButton) {
			
			// add the venue to the venue list and write to file
			FileHandler.venuesList.add(getVenueValues());
			FileHandler.writeVenuesFile();
			
			// let user know successfully added and to refresh data on left
			JOptionPane.showMessageDialog(this, "Venue successfully added.");
			
			//clear all of the values in the GUI textfields
			clearValues();
			
			//Update View Panel's table
			ViewVenuePanel.updateTable();
			
			//Update Edit Panel's combo box
			EditVenuePanel.refreshComboBox();
			
			//Update Craft Sale Panels with updated venue info
			ViewCraftSalePanel.updateTable();
			EditCraftSalePanel.refreshComboBox();
			AddCraftSalePanel.refreshComboBox();
			
		} 
		
		// When the Clear button is clicked
		if (ae.getSource() == clearButton) {
			
			// clear all of the values in the GUI textfields
			clearValues();
		} 
	} 
	
}