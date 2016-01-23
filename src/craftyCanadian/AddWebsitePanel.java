package craftyCanadian;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AddWebsitePanel extends JPanel implements ActionListener, 
										FocusListener, ItemListener {

	protected JLabel instructionsLabel,
					 provinceLabel, 
					 dateLabel, 
					 addressLabel, 
					 craftSaleNameLabel, 
					 tablePriceLabel, 
					 descriptionLabel; 
	protected JTextField provinceField, 
						 dateField, 
						 addressField, 
						 craftSaleNameField, 
						 tablePriceField, 
						 descriptionField; 
	protected JButton addButton, // button to edit the craftSale values
					  updateButton; // button to update the list of editable craftSales
	protected JPanel comboPanel, // for the combo box
					 formPanel, // the form for adding
					 buttonPanel; // the button panel
	private final int ROWS = 6; // number of rows for layout
	private static JComboBox<CraftSale> combo = new JComboBox<CraftSale>(); // combo box to hold the craft sales
	private int index = -1; // index of combo box
	DateFormat df = new SimpleDateFormat("MMM d, yyyy");
	
	/**
	 * Constructor
	 */
	public AddWebsitePanel() {
		
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
		instructionsLabel = new JLabel("<html><BR>Choose a Craft Sale from the drop-down box below to show " +
								"the data that will be added to the website. <BR>If you want to add this craft " +
								"sale to the website, click the 'Add to Website' button to apply changes.<BR></html>");
		
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
        
		//Create the craftSale name & craft type labels
		provinceLabel = new JLabel("Province:", JLabel.TRAILING);
		dateLabel = new JLabel("Date:", JLabel.TRAILING);
		addressLabel = new JLabel("Address:", JLabel.TRAILING);
		craftSaleNameLabel = new JLabel("Craft Sale Name:", JLabel.TRAILING);
		tablePriceLabel = new JLabel("Table Price:", JLabel.TRAILING);
		descriptionLabel = new JLabel("Description:", JLabel.TRAILING);
		
		//Create the craftSale name & craft type input fields with 20-characters
		provinceField = new JTextField(20);
		dateField = new JTextField(20);
		addressField = new JTextField(20);
		craftSaleNameField = new JTextField(20);
		tablePriceField = new JTextField(20);
		descriptionField = new JTextField(20);
		
		//make fields read-only.
		provinceField.setEditable(false);
		dateField.setEditable(false);
		addressField.setEditable(false);
		craftSaleNameField.setEditable(false);
		tablePriceField.setEditable(false);
		descriptionField.setEditable(false);
		
		//Set labels for fields
		provinceLabel.setLabelFor(provinceField);
		dateLabel.setLabelFor(dateField);
		addressLabel.setLabelFor(addressField);
		craftSaleNameLabel.setLabelFor(craftSaleNameField);
		tablePriceLabel.setLabelFor(tablePriceField);
		descriptionLabel.setLabelFor(descriptionField);

		//Add components to panel
		formPanel.add(provinceLabel);		formPanel.add(provinceField);
		formPanel.add(dateLabel);			formPanel.add(dateField);
		formPanel.add(addressLabel);		formPanel.add(addressField);
		formPanel.add(craftSaleNameLabel);	formPanel.add(craftSaleNameField);
		formPanel.add(tablePriceLabel);		formPanel.add(tablePriceField);
		formPanel.add(descriptionLabel);	formPanel.add(descriptionField);

		
        //Lay out the panel
        SpringUtilities.makeCompactGrid(formPanel,
                                        ROWS, 2,     //rows, cols
                                        6, 6,        //initX, initY
                                        6, 6);       //xPad, yPad
        
        // Add focusListeners to the text fields
        provinceField.addFocusListener(this);
        dateField.addFocusListener(this);
        addressField.addFocusListener(this);
        craftSaleNameField.addFocusListener(this);
        tablePriceField.addFocusListener(this);
        descriptionField.addFocusListener(this);
        
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
		addButton = new JButton("Add Craft Sale to Website");
		buttonPanel.add(updateButton);
		buttonPanel.add(addButton);

		
		//Add action listeners to the two buttons
		addButton.addActionListener(this);
		updateButton.addActionListener(this);
	}
	
	
	/**
	 * The focusGained events for the event listener
	 */
	public void focusGained(FocusEvent fe) {

//		// When the user tabs into the craftSale name textfield
//		if (fe.getSource() == craftSaleNameField) {
//			// Select the contents of the field
//			craftSaleNameField.selectAll();
//		}
//		
//		// When the user tabs into the street textfield
//		if (fe.getSource() == streetField) {
//			// Select the contents of the field
//			streetField.selectAll();
//		}
//		
//		// When the user tabs into the city textfield
//		if (fe.getSource() == cityField) {
//			// Select the contents of the field
//			cityField.selectAll();
//		}
//		
//		// When the user tabs into the type textfield
//		if (fe.getSource() == typeField) {
//			// Select the contents of the field
//			typeField.selectAll();
//		}
//		
//		// When the user tabs into the table capacity textfield
//		if (fe.getSource() == tableCapacityField) {
//			// Select the contents of the field
//			tableCapacityField.selectAll();
//		}
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
		if (ae.getSource() == addButton) {
			
			// add the website item to the website list and write to file
			FileHandler.websiteItemsList.add(getWebsiteItemValues());
			FileHandler.writeWebsiteItemsFile();
			
			// let user know successfully added and to refresh data on left
			JOptionPane.showMessageDialog(this, "Craft Sale successfully added to website.");
			
			//refresh combo box contents
			refreshComboBox();
			
			//Clear values of form
			clearValues();
			
			//Update View Panel's table
			ViewWebsitePanel.updateTable();
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
		
	}
	
	/**
	 * The displayValues() method gets the values for the currently-selected craftSale
	 */
	public void displayValues() {

		if(combo.getSelectedIndex() != -1) {
			provinceField.setText(FileHandler.craftSalesList.get(index).getVenue().getProvince());
			dateField.setText(df.format(FileHandler.craftSalesList.get(index).getDate()));
			addressField.setText(FileHandler.craftSalesList.get(index).getVenue().getStreet());
			craftSaleNameField.setText(FileHandler.craftSalesList.get(index).getCraftSaleName());
			tablePriceField.setText(Double.toString(FileHandler.craftSalesList.get(index).getTablePrice()));
			descriptionField.setText(FileHandler.craftSalesList.get(index).getDescription());

		}
	}
	
	/**
	* The getWebsiteItemValues method gets the values to add to the website.
	* 
	* @return The Website Addition.
	*/
	public String[] getWebsiteItemValues() {
	   String[] websiteAddition = new String[]{
			   provinceField.getText(),
			   dateField.getText(),
			   addressField.getText(),
			   craftSaleNameField.getText(),
			   tablePriceField.getText(),
			   descriptionField.getText()
	   };
	   
	   return websiteAddition;
	}
	   
	/**
    * The clearValues method clears the values in the input fields.
    */
	public void clearValues() {
		provinceField.setText("");
		dateField.setText("");
		addressField.setText("");
		craftSaleNameField.setText("");
		tablePriceField.setText("");
		descriptionField.setText("");
	}
	
}