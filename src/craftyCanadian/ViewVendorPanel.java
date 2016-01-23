package craftyCanadian;

import java.awt.*;
import java.awt.event.*;
import java.util.Collections;

import javax.swing.*;
import javax.swing.table.*;

public class ViewVendorPanel extends JPanel implements ActionListener {

	protected JLabel vendorListLabel; // the label for the vendor list
	protected JTextField searchField;
	protected JButton updateButton, // button that shows all vendors
					  deleteButton, // button to delete vendor
					  searchButton;
	protected JScrollPane scrollPane; // scrollpane for the vendor list
	protected JPanel buttonPanel,
					 searchPanel;
	protected static JTable table; // table to store the existing vendors
	protected static DefaultTableModel tableModel;
	protected String[] columnNames = {"Vendor Name", "Craft Types"}; // table header

	/**
	 * Constructor
	 * 
	 * Creates a ViewVendorPanel that contains the ability to search for a vendor, 
	 * add, edit or delete a vendor and view the information about a vendor.
	 */
	public ViewVendorPanel() {
		
		// create a BorderLayout manager with 10 px gaps
		setLayout(new BorderLayout(10, 10));
		
		//create search panel
		buildSearchPanel();
		
		scrollPane = new JScrollPane();
		table = new JTable();
		scrollPane.setViewportView(table);

		// create the button panel
		buildButtonPanel();
		
		// add the components to the panel
		add(searchPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
        
        tableModel = new DefaultTableModel(columnNames, 0);
        table.setModel(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 60));
		table.setFillsViewportHeight(true);
//		table.setAutoCreateRowSorter(true);
        
		//populate table
		updateTable();
	}
	
	/**
	 * The buildSearchPanel searches for info in the table.
	 */
	private void buildSearchPanel() {
		// create search panel
		searchPanel = new JPanel();
		
		// set the layout to grid
		searchPanel.setLayout(new GridLayout(2,2));
		
		// create the three JButtons and add them to the panel
		searchButton = new JButton("Search by Vendor Name");
		
		// create search field
		searchField = new JTextField(20);
		
		// create the vendor list label
		vendorListLabel = new JLabel("Select from the table below.", JLabel.CENTER);
		
		searchPanel.add(searchField);	searchPanel.add(searchButton);
		searchPanel.add(vendorListLabel); searchPanel.add(new JLabel(" "));
		
		// add action listeners to the button
		searchButton.addActionListener(this);
		searchField.addActionListener(this);

	}
	
	/**
	 * The buildButtonPanel method creates two buttons.
	 */
	private void buildButtonPanel() {
		// create button panel
		buttonPanel = new JPanel();
		
		// set the layout to flow
		buttonPanel.setLayout(new FlowLayout());
		
		// create the three JButtons and add them to the panel
		updateButton = new JButton("Refresh Vendor List");
		deleteButton = new JButton("Delete Selected Vendor");
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
		
		// add action listeners to the buttons
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);

	}
	
	/**
	 * The actionPerformed events for the event listener
	 */
	public void actionPerformed(ActionEvent ae) {
		
		// When the Delete button is clicked
		if (ae.getSource() == deleteButton) {
			
			// only proceed if a row is selected
			if(table.getSelectedRow() != -1) {
				// get the row selected
				int index = table.getSelectedRow();
				
				// remove the corresponding vendor from the vendor list
				FileHandler.vendorsList.remove(index);
				
				//write change to file
				FileHandler.writeVendorsFile();
				
				//update the table with the change
				updateTable();
				
				//Update Edit Panel's combo box
				EditVendorPanel.refreshComboBox();
			}
		} 
		
		// When Show Ads Button is clicked
		if (ae.getSource() == updateButton) {
			
			updateTable();
		}
		
		// When the Search button is clicked or enter is pressed in search field
		if (ae.getSource() == searchField || ae.getSource() == searchButton) {
			
			// if no search term is entered, pop-up telling to enter a search first
			if (searchField.getText().isEmpty()) {
				
				// display message about no search entered
				JOptionPane.showMessageDialog(this, "No search text was entered in the search text box.");
			} else {
				//sort the vendorsList (by vendor name; default)
				Collections.sort(FileHandler.vendorsList);
				
				//Search for the search term entered in the search field.
				Vendor key = new Vendor(searchField.getText(), new String[] {"","",""});
				int pos = Collections.binarySearch(FileHandler.vendorsList, key);
				
				//update table to show match if found.
				//if no match, display pop-up
				if(pos < 0)
					JOptionPane.showMessageDialog(this, "<html>No vendor found matching that vendor name. " +
												"<BR>Please make sure the name matches exactly.</html>");
				//otherwise update the table
				else {
					updateSearchTable(pos);
					
					//Update Edit Panel's combo box
					EditVendorPanel.refreshComboBox();
				}
			}
		} 
	} 
	
	/**
	 * The updateTable method updates the table to reflect the current list of 
	 * vendors.
	 */
	public static void updateTable() {
		
		if(FileHandler.vendorsList.size() == 0) {
			//do nothing
		} else {
			
			tableModel.setRowCount(0);
			
			for(Vendor t : FileHandler.vendorsList) {
				
				//put craft types string array into a single string
				String[] temp = t.getCraftType();
				String craftTypeString = "";
				
				for(int i = 0; i < temp.length; i++) {

					if(temp[i].equals(""))
						break;
					else if((i+1) == temp.length)
						craftTypeString += temp[i] + ".";
					else if(temp[i+1].equals(""))
						craftTypeString += temp[i] + ".";
					else
						craftTypeString += temp[i] + ", ";
				}
			
				//add data to data table
				tableModel.addRow(new String[] {t.getVendorName(), craftTypeString});
			}
		}
		
		table.tableChanged(null);
	}
	
	/**
	 * The updateSearchTable method updates the table to reflect the vendor that matches the 
	 * search term provided.
	 * 
	 */
	public static void updateSearchTable(int index) {
		
		if(FileHandler.vendorsList.size() == 0) {
			//do nothing
		} else {
			
			tableModel.setRowCount(0);
			
			//put craft types string array into a single string
			String[] temp = FileHandler.vendorsList.get(index).getCraftType();
			String craftTypeString = "";
			
			for(int i = 0; i < temp.length; i++) {

				if(temp[i].equals(""))
					break;
				else if((i+1) == temp.length)
					craftTypeString += temp[i] + ".";
				else if(temp[i+1].equals(""))
					craftTypeString += temp[i] + ".";
				else
					craftTypeString += temp[i] + ", ";
			}
			
			//add only the row matching the index 
			tableModel.addRow(new String[] {FileHandler.vendorsList.get(index).getVendorName(), 
											craftTypeString});
		}
		
		table.tableChanged(null);
	}
}