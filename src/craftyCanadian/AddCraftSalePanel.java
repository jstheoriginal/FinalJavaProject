package craftyCanadian;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddCraftSalePanel extends JPanel implements ActionListener, FocusListener {
	
	protected JLabel saleNameLabel, // vendor name label
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
						 saleVenueField; // venue for the craft sale field
//	protected JTextArea saleDescriptionArea; // craft sale description
	protected JButton addButton, // button to add the vendor
					  clearButton; // button to clear the add vendor input fields
	protected JPanel formPanel, // the form for adding
					 buttonPanel; // the button panel
	protected final int ROWS = 9; // number of rows for layout
	protected static JComboBox<Venue> venueAddCombo = new JComboBox<Venue>(); // combo with venues
	DateFormat df = new SimpleDateFormat("MMM d, yyyy");
	
	/**
	 * Constructor
	 */
	public AddCraftSalePanel() {
		
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
		saleTablePriceField = new JTextField("0.00", 20);
		
		//Create venue combo from list of venues
		for(Venue v : FileHandler.venuesList) {
			venueAddCombo.addItem(v);
		}
		
		//Set Combo to show blank initially
		venueAddCombo.setSelectedIndex(-1);
		
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
		saleVenueLabel.setLabelFor(venueAddCombo);

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
		formPanel.add(saleVenueLabel);		formPanel.add(venueAddCombo);
        
        //Lay out the panel
        SpringUtilities.makeCompactGrid(formPanel,
                                        ROWS, 2, 		 //rows, cols
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
		addButton = new JButton("Add Craft Sale");
		clearButton = new JButton("Clear");
		buttonPanel.add(addButton);
		buttonPanel.add(clearButton);
		
		//Add action listeners to the two buttons
		addButton.addActionListener(this);
		clearButton.addActionListener(this);
	}

	/**
	* The getCraftSaleValues method calls a 9-arg constructor of the CraftSale
	* class passing the contents of the Craft Sale panel text fields.
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
				   (Venue)venueAddCombo.getSelectedItem()
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
		venueAddCombo.setSelectedIndex(-1);
	}
	
	/** 
	 * The refreshComboBox method gets the vendor list from the file and 
	 * recreates the combo box with the most current list of vendors.
	 */
	public static void refreshComboBox() {
		
		//refresh venue combo box contents
		if(venueAddCombo.getItemCount() > 0)
			venueAddCombo.removeAllItems();
		
		//Create venue combo from list of venues
		for(Venue v : FileHandler.venuesList) {
			venueAddCombo.addItem(v);
		}
		
		//select nothing
		venueAddCombo.setSelectedIndex(-1);
	}
	
	@Override
	public void focusGained(FocusEvent fe) {
		// When the user tabs into the table price textfield
		if (fe.getSource() == saleTablePriceField) {
			
			// Select the contents of the field
			saleTablePriceField.selectAll();
		}
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		
		// When the user exits the date textfield
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
		
	}

	/**
	 * The actionPerformed events for the event listener
	 */
	public void actionPerformed(ActionEvent ae) {
		
		// When the Add Button is clicked
		if (ae.getSource() == addButton) {
			
			// add the venue to the venue list and write to file
			FileHandler.craftSalesList.add(getCraftSaleValues());
			FileHandler.writeCraftSalesFile();
			
			// let user know successfully added and to refresh data on left
			JOptionPane.showMessageDialog(this, "Craft Sale successfully added.");
			
			//clear all of the values in the GUI textfields
			clearValues();
			
			//Update View Panel's table
			ViewCraftSalePanel.updateTable();
			
			//Update Edit Panel's combo box
			EditCraftSalePanel.refreshComboBox();
			
			//Update Website Panel's Add Website Panel's combo box
			AddWebsitePanel.refreshComboBox();
			
		} 
		
		// When the Clear button is clicked
		if (ae.getSource() == clearButton) {
			
			// clear all of the values in the GUI textfields
			clearValues();
		} 
	} 
}
