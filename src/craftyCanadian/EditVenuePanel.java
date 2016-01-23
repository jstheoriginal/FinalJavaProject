package craftyCanadian;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class EditVenuePanel extends JPanel implements ActionListener, 
										FocusListener, ItemListener {

	protected JLabel instructionsLabel, // instructions
					 venueNameLabel, // venue name label
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
	protected JButton editButton, // button to edit the venue values
					  updateButton; // button to update the list of editable venues
	protected JPanel comboPanel, // for the combo box
					 formPanel, // the form for adding
					 buttonPanel; // the button panel
	private final int ROWS = 6; // number of rows for layout
	private static JComboBox<Venue> combo = new JComboBox<Venue>(); // combo box to hold the editable venues
	private int index = -1; // index of combo box
	
	/**
	 * Constructor
	 */
	public EditVenuePanel() {
		
        //Create and populate the panel with 10px gaps.
        setLayout(new BorderLayout(10, 10));
	    
		//Build the Form panel
        buildFormPanel();
        
        //Build the Button panel
        buildButtonPanel();
        
        //Build the combo panel
        buildComboPanel();
        
        //Add the combo, form & button panels
        add(comboPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);       

	}
	
	private void buildComboPanel() {
		
		comboPanel = new JPanel(new BorderLayout(10, 10));
		
		//set text for instructions.
		instructionsLabel = new JLabel("<html><BR>Choose a venue from the drop-down box below to show " +
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
		cityLabel.setLabelFor(cityField);
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
		updateButton = new JButton("Refresh Venue List");
		editButton = new JButton("Edit Venue");
		buttonPanel.add(updateButton);
		buttonPanel.add(editButton);

		
		//Add action listeners to the two buttons
		editButton.addActionListener(this);
		updateButton.addActionListener(this);
	}
	
	
	/**
	 * The focusGained events for the event listener
	 */
	public void focusGained(FocusEvent fe) {

		// When the user tabs into the venue name textfield
		if (fe.getSource() == venueNameField) {
			// Select the contents of the field
			venueNameField.selectAll();
		}
		
		// When the user tabs into the street textfield
		if (fe.getSource() == streetField) {
			// Select the contents of the field
			streetField.selectAll();
		}
		
		// When the user tabs into the city textfield
		if (fe.getSource() == cityField) {
			// Select the contents of the field
			cityField.selectAll();
		}
		
		// When the user tabs into the type textfield
		if (fe.getSource() == typeField) {
			// Select the contents of the field
			typeField.selectAll();
		}
		
		// When the user tabs into the table capacity textfield
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
	
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		
		// check if combo box item is selected
		if(ie.getStateChange() == ItemEvent.SELECTED) {
			
			// assign index of currently-selected combo box item
			index = combo.getSelectedIndex();

			// display values for currently-selected combo box item
			displayValues();
		}
	}
	
	/**
	 * The actionPerformed events for the event listener
	 */
	public void actionPerformed(ActionEvent ae) {
		
		// When the Edit Button is clicked
		if (ae.getSource() == editButton) {
			
			if(combo.getSelectedIndex() > -1) {
				// change the attributes of the venue at the matching index
				FileHandler.venuesList.get(index).setVenueName(venueNameField.getText());
				FileHandler.venuesList.get(index).setStreet(streetField.getText());
				FileHandler.venuesList.get(index).setCity(cityField.getText());
				FileHandler.venuesList.get(index).setProvince(provinceCombo.getSelectedItem().toString());
				FileHandler.venuesList.get(index).setVenueType(typeField.getText());
				FileHandler.venuesList.get(index).setTableCount(Integer.parseInt(tableCapacityField.getText()));
				
				// write the updated venues info to file
				FileHandler.writeVenuesFile();
				
				// let user know successfully added and to refresh data on left
				JOptionPane.showMessageDialog(this, "Venue successfully edited.");
				
				//refresh combo box contents
				refreshComboBox();
				
				//Clear values of form
				clearValues();
				
				//Update View Panel's table
				ViewVenuePanel.updateTable();
			}
		} 
		
		// When update Button is clicked
		if (ae.getSource() == updateButton) {
			
			refreshComboBox();
		}
	} 
	
	/** 
	 * The refreshComboBox method gets the vendor list from the file and 
	 * recreates the combo box with the most current list of vendors.
	 */
	public static void refreshComboBox() {
		
		//refresh combo box contents
		if(combo.getItemCount() > 0)
			combo.removeAllItems();
		
		for(Venue venue : FileHandler.venuesList) {
	    	combo.addItem(venue);
	    }
		
		//select nothing
		combo.setSelectedIndex(-1);
		
	}
	
	/**
	 * The displayValues() method gets the values for the currently-selected venue
	 */
	public void displayValues() {

		if(combo.getSelectedIndex() != -1) {
			venueNameField.setText(FileHandler.venuesList.get(index).getVenueName());
			streetField.setText(FileHandler.venuesList.get(index).getStreet());
			cityField.setText(FileHandler.venuesList.get(index).getCity());
			typeField.setText(FileHandler.venuesList.get(index).getVenueType());
			tableCapacityField.setText(Integer.toString(FileHandler.venuesList.get(index).getTableCount()));
			
			//Set province combo box value
			String provString = FileHandler.venuesList.get(index).getProvince();
			
			for(int i = 0; i < provinceCombo.getItemCount(); i++) {
				if(provString.equals(provinceCombo.getItemAt(i).toString())) {
					provinceCombo.setSelectedIndex(i);
					break;
				}
			}
		}
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
	
}