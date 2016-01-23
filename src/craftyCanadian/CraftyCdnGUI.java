package craftyCanadian;

import java.awt.*;

import javax.swing.*;

public class CraftyCdnGUI extends JPanel {
	
	protected JTabbedPane tabbedPane; // tabbed pane
	protected VendorPanel vendorPanel; // vendor panel
	protected AdPanel adPanel; // advertising panel
	protected CraftSalePanel craftSalePanel; // craft sale panel
	protected VenuePanel venuePanel; // venues panel
	protected WebsitePanel websitePanel; // website panel
//	protected JLabel appDescriptionLabel;
	
	
	/** Constructor */
	public CraftyCdnGUI() {
		
//		// create a BorderLayout manager with 10 px gaps
//		setLayout(new BorderLayout(10, 10));
				
		// create the panels
		vendorPanel = new VendorPanel();
		adPanel = new AdPanel();
		craftSalePanel = new CraftSalePanel();
		venuePanel = new VenuePanel();
		websitePanel = new WebsitePanel();
		
//		//create the jlabel
//		appDescriptionLabel = new JLabel("Crafty Canadian's Website Maintenance Portal");
//		appDescriptionLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		
		// create a tabbed pane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		// add a tab for the vendors panel
		tabbedPane.addTab("Vendors",  null, vendorPanel, "Vendor Maintenance");
		tabbedPane.addTab("Craft Sales", null, craftSalePanel, "Craft Sale Maintenance");
		tabbedPane.addTab("Venues", null, venuePanel, "Venue Maintenance");
		tabbedPane.addTab("Advertising", null, adPanel, "Advertising Maintenance");
		tabbedPane.addTab("Website", null, websitePanel, "Website Maintenance");
		
		// add the label and tabbed pane to the main panel
//		add(appDescriptionLabel, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.SOUTH);
		
	}
}
