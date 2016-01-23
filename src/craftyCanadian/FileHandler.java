package craftyCanadian;

import java.io.*;
import java.util.*;

public class FileHandler {

	public static List<Venue> venuesList = new ArrayList<Venue>();
	public static List<Vendor> vendorsList = new ArrayList<Vendor>();
	public static List<Ad> adsList = new ArrayList<Ad>();
	public static List<CraftSale> craftSalesList = new ArrayList<CraftSale>();
	public static List<String[]> websiteItemsList = new ArrayList<String[]>();
	public static String[] websiteTableHeader = new String[6];
	
	/**
	 * The readVenuesFile method reads the venue file, loads each Venue object into 
	 * the Venue ArrayList in memory.
	 */
	@SuppressWarnings("unchecked")
	public synchronized static void readVenuesFile() {
		
		// transfer existing venue list in file to memory
		try {
			
			File venuesFile = new File("Venues.dat");
			
			// only if file exists & has data
			if(venuesFile.exists() && venuesFile.length() != 0) {

				// create new object input stream
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(venuesFile));
				
				// add venues to venue list while venues exist in the file
				venuesList = (ArrayList<Venue>)ois.readObject();
				
				ois.close(); // close inputstream
				
			} else { // file doesn't exist so create it
				venuesFile.createNewFile();
			}
			
		} catch (IOException e) {
			// pop up error message
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// pop up error message
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * The readCraftSalesFile method reads the craft sales file, loads each CraftSale object into 
	 * the CraftSale ArrayList in memory.
	 */
	@SuppressWarnings("unchecked")
	public synchronized static void readCraftSalesFile() {
		
		// transfer existing craft sale list in file to memory
		try {
			
			File craftSalesFile = new File("CraftSales.dat");
			
			// only if file exists & has data
			if(craftSalesFile.exists() && craftSalesFile.length() != 0) {

				// create new object input stream
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(craftSalesFile));
				
				// add craft sale to craft sale list while craft sales exist in the file
				craftSalesList = (ArrayList<CraftSale>)ois.readObject();
				
				ois.close(); // close inputstream
				
			} else { // file doesn't exist so create it
				craftSalesFile.createNewFile();
			}
			
		} catch (IOException e) {
			// pop up error message
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// pop up error message
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * The readVendorsFile method reads the vendors file, loads each Vendor object into 
	 * the Vendors ArrayList in memory.
	 */
	@SuppressWarnings("unchecked")
	public synchronized static void readVendorsFile() {
		
		// transfer existing craft sale list in file to memory
		try {
			
			File vendorsFile = new File("Vendors.dat");
			
			// only if file exists & has data
			if(vendorsFile.exists() && vendorsFile.length() != 0) {

				// create new object input stream
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(vendorsFile));
				
				// add vendors to the vendors list while vendors exist in the file
				vendorsList = (ArrayList<Vendor>)ois.readObject();
				
				ois.close(); // close inputstream
				
			} else { // file doesn't exist so create it
				vendorsFile.createNewFile();
			}
			
		} catch (IOException e) {
			// pop up error message
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// pop up error message
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * The readAdsFile method reads the ads file, loads each Ad object into 
	 * the Ads ArrayList in memory.
	 */
	@SuppressWarnings("unchecked")
	public synchronized static void readAdsFile() {
		
		// transfer existing ad list in file to memory
		try {
			
			File adsFile = new File("Ads.dat");
			
			// only if file exists & has data
			if(adsFile.exists() && adsFile.length() != 0) {

				// create new object input stream
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(adsFile));
				
				// add ad to ad list while ads exist in the file
				adsList = (ArrayList<Ad>)ois.readObject();
				
				ois.close(); // close inputstream
				
			} else { // file doesn't exist so create it
				adsFile.createNewFile();
			}
			
		} catch (IOException e) {
			// pop up error message
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// pop up error message
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * The readWebsiteFile method reads the website file.
	 */
	public synchronized static void readWebsiteFile() {
		
		// transfer existing website list in file to memory
		try {
			
			File websiteFile = new File("craftinfo.txt");
			
			// only if file exists & has data
			if(websiteFile.exists() && websiteFile.length() != 0) {

				// create new object input stream
				BufferedReader br = new BufferedReader(new FileReader(websiteFile));
				String line = null;
				
				//read first line website table header
				line = br.readLine();
				websiteTableHeader = line.split(", ");
				
				//read remaining data from file
				while ((line = br.readLine()) != null) {
					String[] values = line.split("; ");
					websiteItemsList.add(values);
				}
				
				br.close();
				
			} else { // file doesn't exist so create it
				websiteFile.createNewFile();
			}
			
		} catch (IOException e) {
			// pop up error message
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// pop up error message
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * The writeVenuesFile method writes the current venues list in memory to the 
	 * venues file.
	 */
	public synchronized static void writeVenuesFile() {

		// Write the updated vendor list to the venue file
		try {
			
			File venuesFile = new File("Venues.dat");
			
			// Create the OutputStream to the file
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(venuesFile));
			
			try {
				// Write the Venues List to the file
				oos.writeObject(venuesList);
		
			} finally {
				oos.close();
			}
			
		} catch (IOException ioe) {
			// pop up error message
			System.out.println(ioe.getMessage());
		} 
	}
	
	/**
	 * The writeCraftSalesFile method writes the current craftSales list in memory to the 
	 * craftSales file.
	 */
	public synchronized static void writeCraftSalesFile() {

		// Write the updated vendor list to the craftsale file
		try {
			
			File craftSalesFile = new File("CraftSales.dat");
			
			// Create the OutputStream to the file
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(craftSalesFile));
			
			try {
				// Write the CraftSales List to the file
				oos.writeObject(craftSalesList);
		
			} finally {
				oos.close();
			}
			
		} catch (IOException ioe) {
			// pop up error message
			System.out.println(ioe.getMessage());
		} 
	}
	
	/**
	 * The writeVendorsFile method writes the current vendors list in memory to the 
	 * vendors file.
	 */
	public synchronized static void writeVendorsFile() {

		// Write the updated vendor list to the vendors file
		try {
			
			File vendorsFile = new File("Vendors.dat");
			
			// Create the OutputStream to the file
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(vendorsFile));
			
			try {
				// Write the Vendors List to the file
				oos.writeObject(vendorsList);
		
			} finally {
				oos.close();
			}
			
		} catch (IOException ioe) {
			// pop up error message
			System.out.println(ioe.getMessage());
		} 
	}
	
	/**
	 * The writeAdsFile method writes the current ads list in memory to the 
	 * ads file.
	 */
	public synchronized static void writeAdsFile() {

		// Write the updated vendor list to the ads file
		try {
			
			File adsFile = new File("Ads.dat");
			
			// Create the OutputStream to the file
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(adsFile));
			
			try {
				// Write the Ads List to the file
				oos.writeObject(adsList);
		
			} finally {
				oos.close();
			}
			
		} catch (IOException ioe) {
			// pop up error message
			System.out.println(ioe.getMessage());
		} 
	}
	
	/**
	 * The writeWebsiteFile method writes the website data to the file to update the site.
	 */
	public synchronized static void writeWebsiteItemsFile() {

		// Write the updated website file
		try {
			
			File websiteFile = new File("craftinfo.txt");
			
			try {
				
				// create new object input stream
				BufferedWriter br = new BufferedWriter(new FileWriter(websiteFile));
				
				//store website table header
				br.write(websiteTableHeader[0] + ", " + 
						websiteTableHeader[1] + ", " + 
						websiteTableHeader[2] + ", " + 
						websiteTableHeader[3] + ", " + 
						websiteTableHeader[4] + ", " + 
						websiteTableHeader[5]);
				//write a new line
				br.newLine();
				
				for(String[] sa : websiteItemsList) {
					br.write(sa[0] + "; " + 
						sa[1] + "; " + 
						sa[2] + "; " + 
						sa[3] + "; " + 
						sa[4] + "; " + 
						sa[5] + "; ");
					br.newLine();
				}
				
				br.close();
		
			} finally {
				
			}
			
		} catch (IOException ioe) {
			// pop up error message
			System.out.println(ioe.getMessage());
		} 
	}
	
}
