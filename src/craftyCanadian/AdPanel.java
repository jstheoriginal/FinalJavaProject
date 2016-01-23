package craftyCanadian;

import java.awt.*;
import javax.swing.*;

public class AdPanel extends JPanel {
	
	protected JTabbedPane tabbedPane; // tabbed pane
	protected JPanel titlePanel; // panel for the title
	protected JLabel titleLabel; // label for the title
	protected Font titleFont; // font for the title
	protected ViewAdPanel viewAdPanel; // panel to edit/delete ads
	protected AddAdPanel addAdPanel; // panel to add ads
	protected EditAdPanel editAdPanel; // panel to edit/delete ads
	protected JScrollPane scrollPane; // scrollpane for the list of ads
	
	/**
	 * Constructor
	 * 
	 * Creates a VenuePanel that contains the ability to search for an ad, 
	 * add, edit or delete an ad and view the information about an ad.
	 */
	public AdPanel() {
		
		// create the panels
		viewAdPanel = new ViewAdPanel();
		addAdPanel = new AddAdPanel();
		editAdPanel = new EditAdPanel();
		
		// create a tabbed pane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		// add a tab for the vendors panel
		tabbedPane.addTab("View/Delete",  null, viewAdPanel, "View/Delete Ads");
		tabbedPane.addTab("Add", null, addAdPanel, "Add New Ad");
		tabbedPane.addTab("Change", null, editAdPanel, "Change Ad Details");
				
		// add the tabbed pane to the main panel
		add(tabbedPane);
	}
}