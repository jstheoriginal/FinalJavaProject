package craftyCanadian;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class ViewWebsitePanel extends JPanel implements ActionListener {

	protected JLabel websiteListLabel; // the label for the website list
	protected JButton updateButton, // button that shows all websites
					  deleteButton; // button to delete website
	protected JScrollPane scrollPane; // scrollpane for the website list
	protected JPanel buttonPanel;
	protected static JTable table; // table to store the existing websites
	protected static DefaultTableModel tableModel;
	protected String[] columnNames = {"Province", "Date", "Address",
					"Craft Sale Name", "Table Price", "Description"}; // custom table header
	static Locale locale = new Locale("en", "CA");
	static NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
	
	/**
	 * Constructor
	 * 
	 * Creates a ViewWebsitePanel that contains the ability to search for a website, 
	 * add, edit or delete a website and view the information about a website.
	 */
	public ViewWebsitePanel() {
		
		// create a BorderLayout manager with 10 px gaps
		setLayout(new BorderLayout(10, 10));
		
		// create the website list label
		websiteListLabel = new JLabel(
				"Select a craft sale that is currently published on the website:", 
				JLabel.CENTER);
		
		scrollPane = new JScrollPane();
		table = new JTable();
		scrollPane.setViewportView(table);

		// create the button panel
		buildButtonPanel();
		
		// add the components to the panel
		add(websiteListLabel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
        
        tableModel = new DefaultTableModel(columnNames, 0);
        table.setModel(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(800, 60));
		table.setFillsViewportHeight(true);
//		table.setAutoCreateRowSorter(true);
        
		//populate table
		updateTable();
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
		updateButton = new JButton("Refresh Website List");
		deleteButton = new JButton("Delete Selected Website");
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
				
				// remove the corresponding website from the website list
				FileHandler.websiteItemsList.remove(index);
				
				//write change to file
				FileHandler.writeWebsiteItemsFile();
				
				//update the table with the change
				updateTable();
				
//				//Update Edit Panel's combo box
//				EditWebsitePanel.refreshComboBox();
			}
		} 
		
		// When Show Ads Button is clicked
		if (ae.getSource() == updateButton) {
			
			updateTable();
		}
	} 

	
	/**
	 * The updateTable method updates the table to reflect the current list of 
	 * websites
	 */
	public static void updateTable() {
		
		if(FileHandler.websiteItemsList.size() == 0) {
			// do nothing
		} else {
			
			tableModel.setRowCount(0);
			
			for(String[] t : FileHandler.websiteItemsList) {
					
				tableModel.addRow(new String[] {
						t[0], //province
						t[1], //date
						t[2], //address
						t[3], //craft sale name
						nf.format(Double.parseDouble(t[4])), //table price
						t[5] //description
				});				
			}
		}
		
		table.tableChanged(null);
	}
}