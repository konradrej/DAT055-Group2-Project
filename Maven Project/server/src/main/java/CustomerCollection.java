import java.io.*;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class CustomerCollection extends AbstractCollection implements Serializable{
	
	private static final long serialVersionUID = 398906222416370481L;
	private Collection<Customer> allCustomers;
	private final String filename;
	
	/**
	 * Constructor for initializing the CustomerCollection instance
	 * 
	 * @param filename	Filename of the object when serializing
	 */
	
	public CustomerCollection (String filename) {
		this.filename = filename;
		allCustomers = new LinkedList<>();
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
		System.out.println("Customer not found");
	}
	
	/**
	 * Get method for the filename when serializing
	 * 
	 * @return a string of the filename
	 */
	public String getFilename() {
		return this.filename;
	}

	public CustomerCollection readCollection(){
		try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File(this.filename)))){

			CustomerCollection readThis = (CustomerCollection) stream.readObject();
			System.out.println("File has been read");
			return readThis;
		}
		catch(FileNotFoundException e){
			return null;
		}
		catch (ClassNotFoundException e) {
			//something else
			return null;
		}
		catch (IOException e ){
			//something else
			return null;
		}
	}
}
