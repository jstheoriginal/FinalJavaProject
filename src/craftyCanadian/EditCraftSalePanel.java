package craftyCanadian;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class EditCraftSalePanel extends JPanel implements ActionListener, 
										FocusListener, ItemListener {

	protected JLabel instructionsLabel, // instructions
					 saleNameLabel, // vendor name label
					 saleDateLabel, // craft sale date label
					 saleStartTimeLabel, // craft sale start time label
					 saleEndTimeLabel, // craft sale end time label
					 saleThemeLabel, // craft sale theme label
					 saleDescriptionLabel, // description of the craft sale label
					 saleRulesLabel, // rules for the craft sale label
					 saleTablePriceLabel, // price of each table at the craft sale label
					 saleVenueLabel; // venue for the craft sale label
	protected JTextField saleNameField, // vendor name field
						 saleDateField, // craft sale date field
						 saleStartTimeField, // craft sale start time field
						 saleEndTimeField, // craft sale end time field
						 saleThemeField, // craft sale theme field
						 saleDescriptionField, // description of the craft sale field
						 saleRulesField, // rules for the craft sale field
						 saleTablePriceField, // price of each table at the craft sale field
						 saleVenueField; // venue for the craft sale field	protected enum Province {AB, BC, MB, NB, NL, NS, NT, NU, ON, PE, QC, SK, YT} // valid provinces
	protected JButton editButton, // button to edit the craftSale values
					  updateButton; // button to update the list of editable craftSales
	protected JPanel comboPanel, // the panel for the combo box
					 formPanel, // the form for adding
					 buttonPanel; // the button panel
	protected static JComboBox<Venue> venueEditCombo = new JComboBox<Venue>(); // combo box with existing venues
	protected final int ROWS = 9; // number of rows for layout
	protected static JComboBox<CraftSale> combo = new JComboBox<CraftSale>(); // combo box to hold the editable craft sales
	protected int index = -1; // index of combo box
	DateFormat df = new SimpleDateFormat("MMM d, yyyy");
	
	
	/**
	 * Constructor
	 */
	public EditCraftSalePanel() {
		
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
		instructionsLabel = new JLabel("<html><BR>Choose a craft sale from the drop-down box below to show " +
								"its current attributes. <BR>Change any values required and click " +
								"the 'Edit Craft Sale' button to apply changes.<BR></html>");
		
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
        
		//Create the vendor name & craft type labels
		saleNameLabel = new JLabel("Craft Sale Name:", JLabel.TRAILING);
		saleDateLabel = new JLabel("Date:", JLabel.TRAILING);
		saleStartTimeLabel = new JLabel("Start Time:", JLabel.TRAILING);
		saleEndTimeLabel = new JLabel("End Time:", JLabel.TRAILING);
		saleThemeLabel = new JLabel("Theme:", JLabel.TRAILING);
		saleDescriptionLabel = new JLabel("Description:", JLabel.TRAILING);
		saleRulesLabel = new JLabel("Rules:", JLabel.TRAILING);
		saleTablePriceLabel = new JLabel("Table Price:", JLabel.TRAILING);
		saleVenueLabel = new JLabel("Venue:", JLabel.TRAILING);

		//Create the vendor name & craft type input fields with 20-characters
		saleNameField = new JTextField(20);
		saleDateField = new JTextField(20);
		saleStartTimeField = new JTextField(20);
		saleEndTimeField = new JTextField(20);
		saleThemeField = new JTextField(20);
		saleDescriptionField = new JTextField(20);
//		saleDescriptionArea = new JTextArea(2, 20);
		saleRulesField = new JTextField(20);
		saleTablePriceField = new JTextField(20);
		
		//Create venue combo from list of venues
		for(Venue v : FileHandler.venuesList) {
			venueEditCombo.addItem(v);
		}
		
		//Set Combo to show blank initially
		venueEditCombo.setSelectedIndex(-1);
		
		//Set labels for fields
		saleNameLabel.setLabelFor(saleNameField);
		saleDateLabel.setLabelFor(saleDateField);
		saleStartTimeLabel.setLabelFor(saleStartTimeField);
		saleEndTimeLabel.setLabelFor(saleEndTimeField);
		saleThemeLabel.setLabelFor(saleThemeField);
		saleDescriptionLabel.setLabelFor(saleDescriptionField);
//		saleDescriptionLabel.setLabelFor(saleDescriptionArea);
		saleRulesLabel.setLabelFor(saleRulesField);
		saleTablePriceLabel.setLabelFor(saleTablePriceField);
		saleVenueLabel.setLabelFor(venueEditCombo);

		//Add components to panel
		formPanel.add(saleNameLabel);		formPanel.add(saleNameField);
		formPanel.add(saleDateLabel);		formPanel.add(saleDateField);
		formPanel.add(saleStartTimeLabel);	formPanel.add(saleStartTimeField);
		formPanel.add(saleEndTimeLabel);	formPanel.add(saleEndTimeField);
		formPanel.add(saleThemeLabel);		formPanel.add(saleThemeField);
		formPanel.add(saleDescriptionLabel); formPanel.add(saleDescriptionField);
//		formPanel.add(saleDescriptionLabel); formPanel.add(saleDescriptionArea);
		formPanel.add(saleRulesLabel);		formPanel.add(saleRulesField);
		formPanel.add(saleTablePriceLabel);	formPanel.add(saleTablePriceField);
		formPanel.add(saleVenueLabel);		formPanel.add(venueEditCombo);

        //Lay out the panel
        SpringUtilities.makeCompactGrid(formPanel,
                                        ROWS, 2,     //rows, cols
                                        6, 6,        //initX, initY
                                        6, 6);       //xPad, yPad
        
        // Add focusListeners to the text fields
		saleNameField.addFocusListener(this);
		saleDateField.addFocusListener(this);
		saleStartTimeField.addFocusListener(this);
		saleEndTimeField.addFocusListener(this);
		saleThemeField.addFocusListener(this);
		saleDescriptionField.addFocusListener(this);
		saleRulesField.addFocusListener(this);
		saleTablePriceField.addFocusListener(this);
		
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
		updateButton = new JButton("Refresh Craft Sale List");
		editButton = new JButton("Edit Craft Sale");
		buttonPanel.add(updateButton);
		buttonPanel.add(editButton);
		
		//Add action listeners to the two buttons
		editButton.addActionListener(this);
		updateButton.addActionListener(this);
	}
	
	/**
	* The getCraftSaleValues method calls a 9-arg constructor of the CraftSale
	* class passing the contents of the CraftSale panel textfields.
	* 
	* @return The CraftSale object that was created.
	*/
	public CraftSale getCraftSaleValues() {
	    
		CraftSale craftSale = null;
		
		try {
			craftSale = new CraftSale(
				   saleNameField.getText(),
				   df.parse(saleDateField.getText()),
				   saleStartTimeField.getText(),
				   saleEndTimeField.getText(),
				   saleThemeField.getText(),
				   saleDescriptionField.getText(),
				   saleRulesField.getText(),
				   Double.parseDouble(saleTablePriceField.getText()),
				   (Venue)venueEditCombo.getSelectedItem()
				   );
			
		} catch (NumberFormatException nfe) {
			// display number format exception
			JOptionPane.showMessageDialog(this, nfe.getMessage());
		} catch (ParseException pe) {
			// display parse exception
			JOptionPane.showMessageDialog(this, pe.getMessage());
		}
		
		return craftSale;
		
	}
	   
	/**
    * The clearValues method clears the values in the input fields.
    */
	public void clearValues() {
		saleNameField.setText("");
		saleDateField.setText("");
		saleStartTimeField.setText("");
		saleEndTimeField.setText("");
		saleThemeField.setText("");
		saleDescriptionField.setText("");
		saleRulesField.setText("");
		saleTablePriceField.setText("");
		venueEditCombo.setSelectedIndex(-1);
	}
	
	/**
	 * The focusGained events for the event listener
	 */
	public void focusGained(FocusEvent fe) {

		// When the user tabs into the craft sale name textfield
		if (fe.getSource() == saleNameField) {
			// Select the contents of the field
			saleNameField.selectAll();
		}
		
		// When the user tabs into the date textfield
		if (fe.getSource() == saleDateField) {
			// Select the contents of the field
			saleDateField.selectAll();
		}
		
		// When the user tabs into the start time textfield
		if (fe.getSource() == saleStartTimeField) {
			// Select the contents of the field
			saleStartTimeField.selectAll();
		}
		
		// When the user tabs into the end time textfield
		if (fe.getSource() == saleEndTimeField) {
			// Select the contents of the field
			saleEndTimeField.selectAll();
		}
		
		// When the user tabs into the theme textfield
		if (fe.getSource() == saleThemeField) {
			// Select the contents of the field
			saleThemeField.selectAll();
		}
		
		// When the user tabs into the theme textfield
		if (fe.getSource() == saleDescriptionField) {
			// Select the contents of the field
			saleDescriptionField.selectAll();
		}
		
		// When the user tabs into the theme textfield
		if (fe.getSource() == saleRulesField) {
			// Select the contents of the field
			saleRulesField.selectAll();
		}
		
		// When the user tabs into the theme textfield
		if (fe.getSource() == saleTablePriceField) {
			// Select the contents of the field
			saleTablePriceField.selectAll();
		}
		
	}

	/**
	 * The focusLost events for the event handler
	 */
	public void focusLost(FocusEvent fe) {
		
		// When the user tabs into the date textfield
		if (fe.getSource() == saleDateField) {
			// Select the contents of the field
			try {
				//check to make sure it's in the correct format
				CraftyCdnClient.pastDateTest(df.parse(saleDateField.getText()));
			} catch (ParseException pe) {
				// let user know string entered isn't valid date
				JOptionPane.showMessageDialog(this, "Please enter a valid date in the format \"Feb 3, 1982\".");
			} catch (PastDateException pde) {
				// let user know date is in the past
				JOptionPane.showMessageDialog(this, pde.getMessage());
			}
		}
		
		// when user exits the table price field, check whether a valid number
		if (fe.getSource() == saleTablePriceField) {
			
			try {
				Double.parseDouble(saleTablePriceField.getText());
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
				// change the attributes of the craft sale at the matching index
				FileHandler.craftSalesList.get(index).setCraftSaleName(saleNameField.getText());
				try {
					FileHandler.craftSalesList.get(index).setDate(df.parse(saleDateField.getText()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
				FileHandler.craftSalesList.get(index).setStartTime(saleStartTimeField.getText());
				FileHandler.craftSalesList.get(index).setEndTime(saleEndTimeField.getText());
				FileHandler.craftSalesList.get(index).setTheme(saleThemeField.getText());
				FileHandler.craftSalesList.get(index).setDescription(saleDescriptionField.getText());
				FileHandler.craftSalesList.get(index).setRules(saleRulesField.getText());
				FileHandler.craftSalesList.get(index).setTablePrice(Double.parseDouble(saleTablePriceField.getText()));
				FileHandler.craftSalesList.get(index).setVenue((Venue)venueEditCombo.getSelectedItem());
				
				// write the updated venues info to file
				FileHandler.writeCraftSalesFile();
				
				// let user know successfully added and to refresh data on left
				JOptionPane.showMessageDialog(this, "Craft Sale successfully edited.");
				
				//refresh combo box contents
				refreshComboBox();
				
				//Clear values of form
				clearValues();
				
				//Update View Panel's table
				ViewCraftSalePanel.updateTable();
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
		
		for(CraftSale craftSale : FileHandler.craftSalesList) {
	    	combo.addItem(craftSale);
	    }
		
		//select nothing
		combo.setSelectedIndex(-1);
		
		//refresh venue combo box contents
		if(venueEditCombo.getItemCount() > 0)
			venueEditCombo.removeAllItems();
		
		//Create venue combo from list of venues
		for(Venue v : FileHandler.venuesList) {
			venueEditCombo.addItem(v);
		}
		
		//select nothing
		venueEditCombo.setSelectedIndex(-1);
		
	}
	
	/**
	 * The displayValues() method gets the values for the currently-selected craft sale
	 */
	public void displayValues() {

		if(combo.getSelectedIndex() != -1) {
			
			saleNameField.setText(FileHandler.craftSalesList.get(index).getCraftSaleName());
			saleDateField.setText(df.format(FileHandler.craftSalesList.get(index).getDate()));
			saleStartTimeField.setText(FileHandler.craftSalesList.get(index).getStartTime());
			saleEndTimeField.setText(FileHandler.craftSalesList.get(index).getEndTime());
			saleThemeField.setText(FileHandler.craftSalesList.get(index).getTheme());
			saleDescriptionField.setText(FileHandler.craftSalesList.get(index).getDescription());
			saleRulesField.setText(FileHandler.craftSalesList.get(index).getRules());
			saleTablePriceField.setText(Double.toString(FileHandler.craftSalesList.get(index).getTablePrice()));
			
			//Set Venue combo box value
			String csVenue = FileHandler.craftSalesList.get(index).getVenue().toString();
			
			for(Venue v : FileHandler.venuesList) {

				if(csVenue.equals(v.toString())) {
					venueEditCombo.setSelectedItem(v);
					break;
				}
			}
		}
	}
}
