package craftyCanadian;

import java.awt.*;
import javax.swing.*;

public class VenuePanel extends JPanel {
	
	protected JPanel titlePanel; // panel for the title
	protected JLabel titleLabel; // label for the title
	protected Font titleFont; // font for the title
	protected ViewVenuePanel viewVenuePanel; // panel to edit/delete venues
	protected AddVenuePanel addVenuePanel; // panel to add venues
	protected EditVenuePanel editVenuePanel; // panel to edit venues
	protected JScrollPane scrollPane; // scrollpane for the list of venues
	protected JTabbedPane tabbedPane; // tabbed pane
	
	/**
	 * Constructor
	 * 
	 * Creates a VenuePanel that contains the ability to search for a venue, 
	 * add, edit or delete a Venue and view the information about a venue.
	 */
	public VenuePanel() {
		
		// create the panels
		viewVenuePanel = new ViewVenuePanel();
		addVenuePanel = new AddVenuePanel();
		editVenuePanel = new EditVenuePanel();
		
		// create a tabbed pane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		// add a tab for the Venues panel
		tabbedPane.addTab("View/Delete",  null, viewVenuePanel, "View/Delete Venues");
		tabbedPane.addTab("Add", null, addVenuePanel, "Add New Venue");
		tabbedPane.addTab("Change", null, editVenuePanel, "Change Venue Details");
				
		// add the tabbed pane to the main panel
		add(tabbedPane);
		
	}
}
