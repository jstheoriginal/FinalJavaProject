package craftyCanadian;

import java.awt.*;
import javax.swing.*;

public class WebsitePanel extends JPanel {
	
	protected JPanel titlePanel; // panel for the title
	protected JLabel titleLabel; // label for the title
	protected Font titleFont; // font for the title
	protected ViewWebsitePanel viewWebsitePanel; // panel to edit/delete websites
	protected AddWebsitePanel addWebsitePanel; // panel to add websites
//	protected EditWebsitePanel editWebsitePanel; // panel to edit websites
	protected JScrollPane scrollPane; // scrollpane for the list of websites
	protected JTabbedPane tabbedPane; // tabbed pane
	
	/**
	 * Constructor
	 * 
	 * Creates a WebsitePanel that contains the ability to search for a website, 
	 * add, edit or delete a Website and view the information about a website.
	 */
	public WebsitePanel() {
		
		// create the panels
		viewWebsitePanel = new ViewWebsitePanel();
		addWebsitePanel = new AddWebsitePanel();
//		editWebsitePanel = new EditWebsitePanel();
		
		// create a tabbed pane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		// add a tab for the Websites panel
		tabbedPane.addTab("View/Delete",  null, viewWebsitePanel, "View/Delete Websites");
		tabbedPane.addTab("Add", null, addWebsitePanel, "Add New Website");
//		tabbedPane.addTab("Change", null, editWebsitePanel, "Change Website Details");
				
		// add the tabbed pane to the main panel
		add(tabbedPane);
		
	}
}
