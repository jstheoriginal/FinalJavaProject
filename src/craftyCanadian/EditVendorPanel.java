package craftyCanadian;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class EditVendorPanel extends JPanel implements ActionListener, FocusListener, 
											ItemListener {

	protected JLabel instructionsLabel, //instructions
					 vendorNameLabel, // vendor name label
	 				 craftTypes1Label, // the craft type label 1
	 				 craftTypes2Label, // the craft type label 2
	 				 craftTypes3Label; // the craft type label 3
	protected JTextField vendorNameField, // the field for the vendor name
		 				 craftTypes1Field, // the field for entering the craft types 1
		 				 craftTypes2Field, // the field for entering the craft types 1
		 				 craftTypes3Field; // the field for entering the craft types 1
	protected JButton editButton, // button to add the vendor
					  updateButton; // button to update the list of vendors
	protected JPanel comboPanel, //panel for the combo box
					 formPanel, // the form for adding vendor panels
	 				 buttonPanel; // the button panel
	protected final int ROWS = 12; // number of rows for layout
	private static JComboBox<Vendor> combo = new JComboBox<Vendor>(); // combo box for selector
	private int index = -1;// index of combo box

	/**
	* Constructor
	*/
	public EditVendorPanel() {	
		
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
	    
		// add action listener to the combo box
		combo.addItemListener(this);
	}
	
	private void buildComboPanel() {
		
		comboPanel = new JPanel(new BorderLayout(10, 10));
		
		//set text for instructions.
		instructionsLabel = new JLabel("<html><BR>Choose a vendor from the drop-down box below to show " +
								"its current attributes. <BR>Change any values required and click " +
								"the 'Edit Vendor' button to apply changes.<BR></html>");
		
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
		vendorNameLabel = new JLabel("Vendor Name:", JLabel.TRAILING);
		craftTypes1Label = new JLabel("Craft Type 1:", JLabel.TRAILING);
		craftTypes2Label = new JLabel("Craft Type 2:", JLabel.TRAILING);
		craftTypes3Label = new JLabel("Craft Type 3:", JLabel.TRAILING);
		
		//Create the vendor name & craft type input fields with 15-characters
		vendorNameField = new JTextField(20);
		craftTypes1Field = new JTextField(20);
		craftTypes2Field = new JTextField(20);
		craftTypes3Field = new JTextField(20);
		
		//Set labels for fields
		vendorNameLabel.setLabelFor(vendorNameField);
		craftTypes1Label.setLabelFor(craftTypes1Field);
		craftTypes2Label.setLabelFor(craftTypes2Field);
		craftTypes3Label.setLabelFor(craftTypes3Field);

		//Add components to panel
		formPanel.add(vendorNameLabel);		formPanel.add(vendorNameField);
		formPanel.add(craftTypes1Label);	formPanel.add(craftTypes1Field);
		formPanel.add(craftTypes2Label);	formPanel.add(craftTypes2Field);
		formPanel.add(craftTypes3Label);	formPanel.add(craftTypes3Field);
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
		vendorNameField.addFocusListener(this);
		craftTypes1Field.addFocusListener(this);
		craftTypes2Field.addFocusListener(this);
		craftTypes3Field.addFocusListener(this);
	
	}
	
	/**
	 * The buildButtonPanel method creates two buttons.
	 */
	private void buildButtonPanel() {
		
		//Create button panel
		buttonPanel = new JPanel();
		
		//Set the layout to flow
		buttonPanel.setLayout(new FlowLayout());
		
		//Create the JButtons and add them to the panel
		updateButton = new JButton("Refresh Vendor List");
		editButton = new JButton("Edit Vendor");
		buttonPanel.add(editButton);
		buttonPanel.add(updateButton);

		
		//Add action listeners to the buttons
		updateButton.addActionListener(this);
		editButton.addActionListener(this);
	}
	
	/**
	* The getAdValues method calls a two-arg constructor of the Vendor
	* class passing the contents of the Vendor panel textfields.
	* 
	* @return The bookInventory object that was created.
	*/
	public Vendor getVendorValues() {
	   Vendor vendor = new Vendor(
		   vendorNameField.getText(),
		   new String[] {
			   craftTypes1Field.getText(),
			   craftTypes2Field.getText(),
			   craftTypes3Field.getText()
		   }
		   );
	   
	   return vendor;
	}
	   
	/**
    * The clearValues method clears the values in the input fields.
    */
	public void clearValues() {
		vendorNameField.setText("");
		craftTypes1Field.setText("");
		craftTypes2Field.setText("");
		craftTypes3Field.setText("");
	}
	
	/** 
	 * The refreshComboBox method gets the vendor list from the file and 
	 * recreates the combo box with the most current list of vendors.
	 */
	public static void refreshComboBox() {
		
		//refresh combo box contents
		if(combo.getItemCount() > 0)
			combo.removeAllItems();
		
		for(Vendor vendor : FileHandler.vendorsList) {
	    	combo.addItem(vendor);
	    }
		
		//select nothing
		combo.setSelectedIndex(-1);
	}
	
	/**
	 * The displayValues() method gets the values for the currently-selected vendor
	 */
	public void displayValues() {

		String[] craftTypesString = FileHandler.vendorsList.get(index).getCraftType();
		
		
		if(combo.getSelectedIndex() != -1) {
			vendorNameField.setText(FileHandler.vendorsList.get(index).getVendorName());
			craftTypes1Field.setText(craftTypesString[0]);
			craftTypes2Field.setText(craftTypesString[1]);
			craftTypes3Field.setText(craftTypesString[2]);
			
		}
	}
	
	/**
	 * The focusGained events for the event listener
	 */
	public void focusGained(FocusEvent fe) {
		
	//	refreshComboBox();
		
		// When the user tabs into the vendor name textfield
		if (fe.getSource() == vendorNameField) {
			// Select the contents of the field
			vendorNameField.selectAll();
		}
		
		// When the user tabs into the craft types 1 textfield
		if (fe.getSource() == craftTypes1Field) {
			// Select the contents of the field
			craftTypes1Field.selectAll();
		}
		
		// When the user tabs into the craft types 2 textfield
		if (fe.getSource() == craftTypes2Field) {
			// Select the contents of the field
			craftTypes2Field.selectAll();
		}
		
		// When the user tabs into the craft types 3 textfield
		if (fe.getSource() == craftTypes3Field) {
			// Select the contents of the field
			craftTypes3Field.selectAll();
		}
	}

	/**
	 * The focusLost events for the event handler
	 */
	public void focusLost(FocusEvent fe) {
		
		
	} // end focusLost
	
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		
		// check if combo box item is selected
		if(ie.getStateChange() == ItemEvent.SELECTED) {
			
			index = combo.getSelectedIndex();

			displayValues();
		}
	}
	
	/**
	 * The actionPerformed events for the event listener
	 */
	public void actionPerformed(ActionEvent ae) {
		
		// When the edit Button is clicked		
		if (ae.getSource() == editButton) {
			
			if(combo.getSelectedIndex() > -1) {
				// change the attributes of the vendor at the matching index
				FileHandler.vendorsList.get(index).setVendorName(vendorNameField.getText());
				FileHandler.vendorsList.get(index).setCraftType(new String[] {
						craftTypes1Field.getText(), craftTypes2Field.getText(), craftTypes3Field.getText()});
				
				// write the updated vendors info to file
				FileHandler.writeVendorsFile();
				
				// let user know successfully added and to refresh data on left
				JOptionPane.showMessageDialog(this, "Vendor successfully edited.");
				
				//refresh combo box contents
				refreshComboBox();
				
				//Clear values of form
				clearValues();
				
				//Update View Panel's table
				ViewVendorPanel.updateTable();
			}
			
		} 
		
		// When update Button is clicked
		if (ae.getSource() == updateButton) {
			
			refreshComboBox();
		}
	} 
}