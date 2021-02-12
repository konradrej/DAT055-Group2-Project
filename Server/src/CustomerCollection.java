import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class CustomerCollection implements Serializable, AllCollections {
	
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
	
	public Collection<AbstractCollectionObject> getCollection() {
		Collection<AbstractCollectionObject> c = new LinkedList <AbstractCollectionObject>();
		for(Customer c2 : this.allCustomers) {
			c.add(c2);
		}
		return c;
	}
}
