package craftyCanadian;

import java.awt.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;


public class CraftyCdnClient extends JFrame {

	private CraftyCdnGUI gui; // the gui instance
	private Container c; // the content container pane
	
	/** Constructor */
	public CraftyCdnClient() {
		
		// Load existing file content to memory.
		FileHandler.readVenuesFile();
		FileHandler.readAdsFile();
		FileHandler.readCraftSalesFile();
		FileHandler.readVendorsFile();
		FileHandler.readWebsiteFile();
		
		// initiate the gui
		gui = new CraftyCdnGUI();
		
		// get the content pane
		c = getContentPane();
		
		// set the content pane to border layout
		c.setLayout(new BorderLayout());
		
		// add the gui to the content pane
		c.add(gui, BorderLayout.CENTER);
		
		/** set JFrame parms & then pack */
		
		// set the window's title
		setTitle("Crafty Canadian Website Publisher");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// pack the frame
		pack();
		
		// centre the JFrame on the centre of the desktop
		setLocationRelativeTo(null);
		
		// set the window to be visible
		setVisible(true);
	}
	
	/**
	 * Custom Exception pastDateTest checks to see if date entered is in the past.
	 * 
	 * @param d
	 * @throws DateFormatException
	 */
    public static void pastDateTest(Date date) throws PastDateException {
        Calendar input = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        
        //set incoming date object to equal input variable
        input.setTime(date);
    	
        // if incoming date is before current date throw exception
    	if(input.compareTo(now) < 0)
    		throw new PastDateException("Date cannot be in the past.");
    }
    
    /**
     * isValidDate tests a string to see whether it is a valid date.
     * @param date
     * @return true if valid date, false if not valid
     */
    public static boolean isValidDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
        Date testDate = null;
        
        try {
            testDate = sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        
        if (!sdf.format(testDate).equals(date)) {
            return false;
        }
        
        return true;
    }

	/**
	 * The main method declares and creates an instance of the CraftyCdnClient class.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		CraftyCdnClient craftyCdnApp = new CraftyCdnClient();

	}

}
