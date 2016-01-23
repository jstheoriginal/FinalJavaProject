package craftyCanadian;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class ViewCraftSalePanel extends JPanel implements ActionListener {

	protected JLabel craftSalesListLabel; // the label for the craftSale list
	protected JTextField searchField;
	protected JButton updateButton, // button that shows all craftSales
					  deleteButton, // button to delete craftSale
					  locationSortButton,
					  dateSortAscButton,
					  dateSortDescButton,
					  searchButton;
	protected JScrollPane scrollPane; // scrollpane for the craftSale list
	protected JPanel buttonPanel,
					 sortPanel;
	protected static JTable table; // table to store the existing craftSales
	protected static DefaultTableModel tableModel;
	protected String[] columnNames = {"Craft Sale Name", "Date", "Start Time", "End Time",
					"Theme", "Description", "Rules", "Table Price", "Venue"}; // table header
	static DateFormat df = new SimpleDateFormat("MMM d, yyyy");
	static Locale locale = new Locale("en", "CA");
	static NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
	
	/**
	 * Constructor
	 * 
	 * Creates a ViewCraftSalePanel that contains the ability to search for a craftSale, 
	 * add, edit or delete a craftSale and view the information about a craftSale.
	 */
	public ViewCraftSalePanel() {
		
		// create a BorderLayout manager with 10 px gaps
		setLayout(new BorderLayout(10, 10));
		
		//create sort panel
		buildSortPanel();
		
		scrollPane = new JScrollPane();
		table = new JTable();
		scrollPane.setViewportView(table);

		// create the button panel
		buildButtonPanel();
		
		// add the components to the panel
		add(sortPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
        
        tableModel = new DefaultTableModel(columnNames, 0);
        table.setModel(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(800, 60));
		table.setFillsViewportHeight(true);
//		table.setAutoCreateRowSorter(true);
		
		//set column widths
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		
		
        //update the table to display current data
		updateTable();
	}
	
	/**
	 * The buildSortPanel sorts the table.
	 */
	private void buildSortPanel() {
		// create sort panel
		sortPanel = new JPanel();
		
		// set the layout to grid
		sortPanel.setLayout(new GridLayout(3,3));
		
		// create the search field and buttons
		searchField = new JTextField(20);
		searchButton = new JButton("Search by Date");
		
		// create the three JButtons and add them to the panel
		locationSortButton = new JButton("Sort by Venue");
		dateSortAscButton = new JButton("Sort by Date (Ascending)");
		dateSortDescButton = new JButton("Sort by Date (Descending)");
		
		// create the craftSale list label
		craftSalesListLabel = new JLabel("Select an existing Craft Sale:", JLabel.CENTER);
		
		//add to row 1
		sortPanel.add(searchField);
		sortPanel.add(searchButton);
		sortPanel.add(new JLabel(" "));
		
		//add to row 2
		sortPanel.add(locationSortButton);	
		sortPanel.add(dateSortAscButton);
		sortPanel.add(dateSortDescButton);
		//add to row 3
		sortPanel.add(craftSalesListLabel); 
		sortPanel.add(new JLabel(" "));
		sortPanel.add(new JLabel(" "));
		
		// add action listeners to the buttons/field
		locationSortButton.addActionListener(this);
		dateSortAscButton.addActionListener(this);
		dateSortDescButton.addActionListener(this);
		searchField.addActionListener(this);
		searchButton.addActionListener(this);

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
		updateButton = new JButton("Refresh Craft Sale List");
		deleteButton = new JButton("Delete Selected Craft Sale");
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
				
				// remove the corresponding craftSale from the craftSale list
				FileHandler.craftSalesList.remove(index);

				//write change to file
				FileHandler.writeCraftSalesFile();
				
				//update the table with the change
				updateTable();
				
				//Update Edit Panel's combo box
				EditCraftSalePanel.refreshComboBox();
				
				//Update Website Panels Add Panel
				AddWebsitePanel.refreshComboBox();
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
				
				//create instance of dateascendingsort
				DateAscSort das = new DateAscSort();
				
				//sort the craftSalesList (using Comparator by date)
				Collections.sort(FileHandler.craftSalesList, das);
				
				//Search for the search term entered in the search field.
				CraftSale key;
				
				try {
					
					//check to make sure it's in the correct format
					CraftyCdnClient.pastDateTest(df.parse(searchField.getText()));
					
					
					key = new CraftSale("", df.parse(searchField.getText()), "", "", "", "", "", 0.0, 
															new Venue("", "", "", "MB", "", 0));
					
					int pos = Collections.binarySearch(FileHandler.craftSalesList, key, das);
					
					//update table to show match if found.
					//if no match, display pop-up
					if(pos < 0)
						JOptionPane.showMessageDialog(this, "No craft sale found matching that date.");
					//otherwise update the table
					else
						updateSearchTable(pos);
					
				} catch (ParseException e) {
					
					JOptionPane.showMessageDialog(this, "Please enter a valid date in the format \"Feb 3, 1982\".");
					
				} catch (PastDateException pde) {
					
					// let user know date is in the past
					JOptionPane.showMessageDialog(this, pde.getMessage());
				}
			}
		} 
		
		// When Location Sort Button is pressed
		if(ae.getSource() == locationSortButton) {
			
			//create new location sort comparator
			LocationSort ls = new LocationSort();
			
			//sort the craftsaleslist by location
			Collections.sort(FileHandler.craftSalesList, ls);
			
			//update the table with the change
			updateTable();
			
			//Update Edit Panel's combo box
			EditCraftSalePanel.refreshComboBox();
			
			//Update Website Panels Add Panel
			AddWebsitePanel.refreshComboBox();
			
		}
		
		// When Date Ascending Sort Button is pressed
		if(ae.getSource() == dateSortAscButton) {
			
			//create new date ascending sort comparator
			DateAscSort das = new DateAscSort();
			
			//sort the craftsaleslist by date (ascending)
			Collections.sort(FileHandler.craftSalesList, das);
			
			//update the table with the change
			updateTable();
			
			//Update Edit Panel's combo box
			EditCraftSalePanel.refreshComboBox();
			
			//Update Website Panels Add Panel
			AddWebsitePanel.refreshComboBox();
			
		}
		
		// When Date Descending Sort Button is pressed
		if(ae.getSource() == dateSortDescButton) {
			
			//create new date descending sort comparator
			DateDescSort dds = new DateDescSort();
			
			//sort the craftsaleslist by date (descending)
			Collections.sort(FileHandler.craftSalesList, dds);
			
			//update the table with the change
			updateTable();
			
			//Update Edit Panel's combo box
			EditCraftSalePanel.refreshComboBox();
			
			//Update Website Panels Add Panel
			AddWebsitePanel.refreshComboBox();
			
		}
	} 
	
	public static void updateTable() {
		
		if(FileHandler.craftSalesList.size() == 0) {
			// do nothing
		} else {
			
			tableModel.setRowCount(0);
			
			for(CraftSale t : FileHandler.craftSalesList) {
				tableModel.addRow(new String[] {
						t.getCraftSaleName(), 
						df.format(t.getDate()),
						t.getStartTime(),
						t.getEndTime(),
						t.getTheme(),
						t.getDescription(),
						t.getRules(),
						nf.format(t.getTablePrice()),
						t.getVenue().getVenueName()
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
		
		if(FileHandler.craftSalesList.size() == 0) {
			//do nothing
		} else {
			
			tableModel.setRowCount(0);
			
			//add only the row matching the index 
			tableModel.addRow(new String[] {
					FileHandler.craftSalesList.get(index).getCraftSaleName(), 
					df.format(FileHandler.craftSalesList.get(index).getDate()),
					FileHandler.craftSalesList.get(index).getStartTime(),
					FileHandler.craftSalesList.get(index).getEndTime(),
					FileHandler.craftSalesList.get(index).getTheme(),
					FileHandler.craftSalesList.get(index).getDescription(),
					FileHandler.craftSalesList.get(index).getRules(),
					nf.format(FileHandler.craftSalesList.get(index).getTablePrice()),
					FileHandler.craftSalesList.get(index).getVenue().getVenueName()
											});
		}
		
		table.tableChanged(null);
	}
}