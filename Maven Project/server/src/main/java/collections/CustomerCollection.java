package collections;

import cinemaObjects.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class CustomerCollection extends AbstractCollection {
	
	private static final long serialVersionUID = 398906222416370481L;
	private final List<Customer> allCustomers;
	private final String filename;
	
	/**
	 * Constructor for initializing the collections.CustomerCollection instance
	 * 
	 * @param filename	Filename of the object when serializing
	 */
	
	public CustomerCollection (String filename) {
		this.filename = filename;
		allCustomers = new ArrayList<>();
	}
	
	/**
	 * Gets a customer given ssn
	 * 
	 * @param ssn - the ssn that being searched
	 * @return a customer
	 */
	
	public Customer getCustomer(String ssn) {
		for(Customer c : allCustomers) {
			if(c.getSSN().equals(ssn)) {
				return c;
			}
		}
		return null;
	}
	/**
	 * Adds a customer to the objects customer collection
	 * 
	 * @param c - the customer being added
	 */
	public void addCustomer(Customer c) {
		this.allCustomers.add(c);
	}
	
	/**
	 * Removes a customer from the objects customer collection
	 * 
	 * @param c - the customer being removed
	 */
	
	public void removeCustomer(Customer c ) {
		for(Customer c2: this.allCustomers) {
			if(c2.equals(c)) {
				this.allCustomers.remove(c2);
			}
		}
		System.out.println("cinemaObjects.Customer not found");
	}
	
	/**
	 * Get method for the filename when serializing
	 * 
	 * @return a string of the filename
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * Read serialized file
	 *
	 * @return CustomerCollectcion of the read file
	 * @exception ClassCastException returns an empty CustomerCollection
	 * @exception FileNotFoundException returns an empty CustomerCollection
	 * @exception NullPointerException returns an empty CustomerCollection
	 * @exception IOException returns null
	 */
	public CustomerCollection readCollection() throws IOException{
		try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream( this.filename))){

			CustomerCollection readThis = (CustomerCollection) stream.readObject();
			System.out.println("File: "  + this.filename + " has been read");
			return readThis;
		}
		catch (ClassNotFoundException | FileNotFoundException | NullPointerException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return new CustomerCollection(this.filename);
		}
		catch (IOException e ){
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
