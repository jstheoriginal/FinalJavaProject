package craftyCanadian;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ViewAdPanel extends JPanel implements ActionListener {

	protected JLabel adListLabel; // the label for the ad list
	protected JButton updateButton, // button that shows all ads
					  deleteButton; // button to delete ad
	protected JScrollPane scrollPane; // scrollpane for the ad list
	protected JPanel buttonPanel;
	protected static JTable table; // table to store the existing ads
	protected static DefaultTableModel tableModel;
	protected String[] columnNames = {"Website Name", "URL"}; // table header
	
	/**
	 * Constructor
	 * 
	 * Creates a AdPanel that contains the ability to search for a ad, 
	 * add, edit or delete a ad and view the information about a ad.
	 */
	public ViewAdPanel() {
		
		// create a BorderLayout manager with 10 px gaps
		setLayout(new BorderLayout(10, 10));
		
		// create the ad list label
		adListLabel = new JLabel("Select an existing ad:", JLabel.CENTER);
		
		scrollPane = new JScrollPane();
		table = new JTable();
		scrollPane.setViewportView(table);

		// create the button panel
		buildButtonPanel();
		
		// add the components to the panel
		add(adListLabel, BorderLayout.NORTH);
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
	 * The buildButtonPanel method creates two buttons.
	 */
	private void buildButtonPanel() {
		// create button panel
		buttonPanel = new JPanel();
		
		// set the layout to flow
		buttonPanel.setLayout(new FlowLayout());
		
		// create the three JButtons and add them to the panel
		updateButton = new JButton("Refresh Ad List");
		deleteButton = new JButton("Delete Selected Ad");
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
				
				// remove the corresponding ad from the ad list
				FileHandler.adsList.remove(index);
				
				//write change to file
				FileHandler.writeAdsFile();
				
				//update the table with the change
				updateTable();
				
				//Update Edit Panel's combo box
				EditAdPanel.refreshComboBox();
			}
		} 
		
		// When Show Ads Button is clicked
		if (ae.getSource() == updateButton) {
			
			updateTable();
		}
	} 
	
	/**
	 * The updateTable method updates the table to reflect the current list of 
	 * ads
	 */
	public static void updateTable() {
		
		if(FileHandler.adsList.size() == 0) {
			//do nothing
		} else {
			
			tableModel.setRowCount(0);
			
			for(Ad t : FileHandler.adsList) {
				tableModel.addRow(new String[] {t.getWebsiteName(), t.getUrl()});
			}
		}
		
		table.tableChanged(null);
	}

}
