package craftyCanadian;

import java.awt.*;
import java.awt.event.*;
import java.util.Collections;

import javax.swing.*;
import javax.swing.table.*;

public class ViewVenuePanel extends JPanel implements ActionListener {

	protected JLabel venueListLabel; // the label for the venue list
	protected JTextField searchField;
	protected JButton updateButton, // button that shows all venues
					  deleteButton, // button to delete venue
					  searchButton;
	protected JScrollPane scrollPane; // scrollpane for the venue list
	protected JPanel buttonPanel,
					 searchPanel;
	protected static JTable table; // table to store the existing venues
	protected static DefaultTableModel tableModel;
	protected String[] columnNames = {"Venue Name", "Street Address", "City/Town",
					"Province", "Venue Type", "Table Count"}; // table header
	
	/**
	 * Constructor
	 * 
	 * Creates a ViewVenuePanel that contains the ability to search for a venue, 
	 * add, edit or delete a venue and view the information about a venue.
	 */
	public ViewVenuePanel() {
		
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
        table.setPreferredScrollableViewportSize(new Dimension(600, 60));
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
		searchButton = new JButton("Search by Venue Name");
		
		// create search field
		searchField = new JTextField(20);
		
		// create the vendor list label
		venueListLabel = new JLabel("Select from the table below.", JLabel.CENTER);
		
		searchPanel.add(searchField);	searchPanel.add(searchButton);
		searchPanel.add(venueListLabel); searchPanel.add(new JLabel(" "));
		
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
		updateButton = new JButton("Refresh Venue List");
		deleteButton = new JButton("Delete Selected Venue");
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
				
				// remove the corresponding venue from the venue list
				FileHandler.venuesList.remove(index);
				
				//write change to file
				FileHandler.writeVenuesFile();
				
				//update the table with the change
				updateTable();
				
				//Update Edit Panel's combo box
				EditVenuePanel.refreshComboBox();
				
				//Update Craft Sale Panels with updated venue info
				ViewCraftSalePanel.updateTable();
				EditCraftSalePanel.refreshComboBox();
				AddCraftSalePanel.refreshComboBox();
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
				//sort the venuesList (by venue name; default)
				Collections.sort(FileHandler.venuesList);
				
				//Search for the search term entered in the search field.
				Venue key = new Venue(searchField.getText(), "", "", "MB", "", 0);
				int pos = Collections.binarySearch(FileHandler.venuesList, key);
				
				//update table to show match if found.
				//if no match, display pop-up
				if(pos < 0)
					JOptionPane.showMessageDialog(this, "<html>No venue found matching that venue name. " +
												"<BR>Please make sure the name matches exactly.</html>");
				//otherwise update the table
				else
					updateSearchTable(pos);
			}
		} 
	} 

	
	/**
	 * The updateTable method updates the table to reflect the current list of 
	 * venues
	 */
	public static void updateTable() {
		
		if(FileHandler.venuesList.size() == 0) {
			// do nothing
		} else {
			
			tableModel.setRowCount(0);
			
			for(Venue t : FileHandler.venuesList) {
				tableModel.addRow(new String[] {
						t.getVenueName(), 
						t.getStreet(),
						t.getCity(),
						t.getProvince(),
						t.getVenueType(),
						Integer.toString(t.getTableCount())
				});
			}
		}
		
		table.tableChanged(null);
	}
	
	/**
	 * The updateSearchTable method updates the table to reflect the venue that matches the 
	 * search term provided.
	 * 
	 */
	public static void updateSearchTable(int index) {
		
		if(FileHandler.venuesList.size() == 0) {
			//do nothing
		} else {
			
			tableModel.setRowCount(0);
			
			//add only the row matching the index 
			tableModel.addRow(new String[] {FileHandler.venuesList.get(index).getVenueName(), 
											FileHandler.venuesList.get(index).getStreet(),
											FileHandler.venuesList.get(index).getCity(),
											FileHandler.venuesList.get(index).getProvince(),
											FileHandler.venuesList.get(index).getVenueType(),
											Integer.toString(FileHandler.venuesList.get(index).getTableCount())
											});
		}
		
		table.tableChanged(null);
	}
}