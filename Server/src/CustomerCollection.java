import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class CustomerCollection {
	private LinkedList <Customer> allCustomers = new LinkedList <Customer>();
	
	public CustomerCollection () {
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
}
