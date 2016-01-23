package craftyCanadian;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AddVendorPanel extends JPanel implements ActionListener {
	
	protected JLabel vendorNameLabel, // vendor name label
					 craftTypes1Label, // the craft type label 1
					 craftTypes2Label, // the craft type label 2
					 craftTypes3Label; // the craft type label 3
	protected JTextField vendorNameField, // the field for the vendor name
						 craftTypes1Field, // the fields for entering the type of crafts 
										  // the vendor supplies
						 craftTypes2Field, 
						 craftTypes3Field;
	protected JButton addButton, // button to add the vendor
					  clearButton; // button to clear the add vendor input fields
	protected JPanel formPanel, // the form for adding
					 buttonPanel; // the button panel
	protected final int ROWS = 14; // number of rows for layout

	/**
	 * Constructor
	 */
	public AddVendorPanel() {
		
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
        
		//Create the vendor name & craft type labels
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
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
		formPanel.add(new JLabel(" "));		formPanel.add(new JLabel(" ")); //blank for spacing
        
        //Lay out the panel
        SpringUtilities.makeCompactGrid(formPanel,
                                        ROWS, 2, 		 //rows, cols
                                        6, 6,        //initX, initY
                                        6, 6);       //xPad, yPad
        
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
		addButton = new JButton("Add Vendor");
		clearButton = new JButton("Clear");
		buttonPanel.add(addButton);
		buttonPanel.add(clearButton);
		
		//Add action listeners to the two buttons
		addButton.addActionListener(this);
		clearButton.addActionListener(this);
	}
	
	/**
	* The getVendorValues method calls a two-arg constructor of the Vendor
	* class passing the contents of the Vendor panel textfields.
	* 
	* @return The Vendor object that was created.
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
	 * The actionPerformed events for the event listener
	 */
	public void actionPerformed(ActionEvent ae) {
		// When the Add Button is clicked
		if (ae.getSource() == addButton) {
			
			// add the vendor to the vendor list and write to file
			FileHandler.vendorsList.add(getVendorValues());
			FileHandler.writeVendorsFile();
			
			// let user know successfully added and to refresh data on left
			JOptionPane.showMessageDialog(this, "Vendor successfully added.");
			
			//clear all of the values in the GUI textfields
			clearValues();
			
			//Update View Panel's table
			ViewVendorPanel.updateTable();
			
			//Update Edit Panel's combo box
			EditVendorPanel.refreshComboBox();
			
		} 
		
		// When the Clear button is clicked
		if (ae.getSource() == clearButton) {
			
			// clear all of the values in the GUI textfields
			clearValues();
		} 
	} 
}
