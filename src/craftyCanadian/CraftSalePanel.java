package craftyCanadian;

import java.awt.*;
import javax.swing.*;

public class CraftSalePanel extends JPanel {
	
	protected JPanel titlePanel; // panel for the title
	protected JLabel titleLabel; // label for the title
	protected Font titleFont; // font for the title
	protected ViewCraftSalePanel viewCraftSalePanel; // panel to edit/delete craftSales
	protected AddCraftSalePanel addCraftSalePanel; // panel to add craftSales
	protected EditCraftSalePanel editCraftSalePanel; // panel to edit craftSales
	protected JScrollPane scrollPane; // scrollpane for the list of craftSales
	protected JTabbedPane tabbedPane; // tabbed pane
	
	/**
	 * Constructor
	 * 
	 * Creates a CraftSalePanel that contains the ability to search for a craftSale, 
	 * add, edit or delete a CraftSale and view the information about a craftSale.
	 */
	public CraftSalePanel() {
		
		// create the panels
		viewCraftSalePanel = new ViewCraftSalePanel();
		addCraftSalePanel = new AddCraftSalePanel();
		editCraftSalePanel = new EditCraftSalePanel();
		
		// create a tabbed pane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		// add a tab for the CraftSales panel
		tabbedPane.addTab("View/Delete",  null, viewCraftSalePanel, "View/Delete Craft Sales");
		tabbedPane.addTab("Add", null, addCraftSalePanel, "Add New Craft Sale");
		tabbedPane.addTab("Change", null, editCraftSalePanel, "Change Craft Sale Details");
				
		// add the tabbed pane to the main panel
		add(tabbedPane);
		
	}
}
