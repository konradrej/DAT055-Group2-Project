import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class CustomerCollection implements Serializable {
	
	private static final long serialVersionUID = 398906222416370481L;
	private Collection <Customer> allCustomers;
	
	public CustomerCollection (final int ID) {
		allCustomers = new LinkedList <Customer> ();
	}
	public Customer getCustomer(int ssn) {
		for(Customer c : allCustomers) {
			// TODO: class Customer method getSsn
			if(c.getSsn == ssn) {
				return c;
			}
		}
		return null;
	}
	
	public void addCustomer(Customer c) {
		this.allCustomers.add(c);
	}
	
	public void removeCustomer(Customer c) {
		for(Customer c2 : this.allCustomers) {
			if(c.equals(c2)) {
				this.allCustomers.remove(c2);
				//Break because only 1(not true) unique customer
				break;
			}
		}
		System.out.println("Customer not found");
	}
}
