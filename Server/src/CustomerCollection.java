import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class CustomerCollection implements Serializable, AllCollections {
	
	private static final long serialVersionUID = 398906222416370481L;
	private Collection <Customer> allCustomers;
	private final String filename;
	
	public CustomerCollection (String filename) {
		this.filename = filename;
		allCustomers = new LinkedList <Customer> ();
	}
	public Customer getCustomer(String ssn) {
		for(Customer c : allCustomers) {
			if(c.GetSSN() == ssn) {
				return c;
			}
		}
		return null;
	}
	
	public void addCustomer(Customer c) {
		this.allCustomers.add(c);
	}
	
	public void removeCustomer(Customer c ) {
		for(Customer c2: this.allCustomers) {
			if(c2.equals(c)) {
				this.allCustomers.remove(c2);
			}
		}
		System.out.println("Customer not found");
	}
	
	public String getFilename() {
		return this.filename;
	}
	
	@Override
	public void updateCollection(){
		
		try {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File(this.filename)));
		stream.writeObject(this);
		stream.close();
		}
		catch (IOException e) {
            System.out.println("Error initializing stream");
        }
		
	}
}
