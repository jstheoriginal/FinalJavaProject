package craftyCanadian;

import java.awt.*;
import javax.swing.*;

public class VendorPanel extends JPanel {
	
	protected JPanel titlePanel; // panel for the title
	protected JLabel titleLabel; // label for the title
	protected Font titleFont; // font for the title
	protected JTabbedPane tabbedPane; // tabbed pane
	protected ViewVendorPanel viewVendorPanel; // panel to edit/delete vendors
	protected AddVendorPanel addVendorPanel; // panel to add vendors
	protected EditVendorPanel editVendorPanel; // panel to edit vendors
	protected JScrollPane scrollPane; // scrollpane for the list of vendors
	
	/**
	 * Constructor
	 * 
	 * Creates a VendorsPanel that contains the ability to search for a vendor, 
	 * add, edit or delete a vendor and view the information about a vendor.
	 */
	public VendorPanel() {
		
		// create the panels
		viewVendorPanel = new ViewVendorPanel();
		addVendorPanel = new AddVendorPanel();
		editVendorPanel = new EditVendorPanel();
		
		// create a tabbed pane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		// add a tab for the vendors panel
		tabbedPane.addTab("View/Delete",  null, viewVendorPanel, "View/Delete Vendors");
		tabbedPane.addTab("Add", null, addVendorPanel, "Add New Vendor");
		tabbedPane.addTab("Change", null, editVendorPanel, "Change Vendor Details");
				
		// add the tabbed pane to the main panel
		add(tabbedPane);
	}
}
