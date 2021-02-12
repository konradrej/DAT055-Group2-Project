package src;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Customer implements AbstractCollectionObject{

	public int getSsn;

	private String name;
	private String phoneNumber;
	private String SSN;
	
	/**
	 * Constructor for initializing the Customer instance
	 * 
	 * @param name			the name of the customer
	 * @param phoneNumber	the phone number of the customer
	 * @param SSN 			the social security number of the customer
	 */
	public Customer(String name, String phoneNumber, String SSN)
	{
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.SSN = SSN;
	}
	
	/**
	 * Method for getting the name of the customer
	 * 
	 * @return		returns the name of the customer
	 */
<<<<<<< HEAD
	public String GetName()
=======
	public Customer getCustomer()
>>>>>>> branch 'main' of https://github.com/konradrej/DAT055-Group2-Project.git
	{
		return this.name;
	}
	
	/**
	 * Method for getting the phone number of the customer
	 * 
	 * @return		returns the phone number of the customer
	 */
	public String GetPhoneNumber()
	{
		return this.phoneNumber;
	}
	
	/**
	 * Method for getting the social security number of the customer
	 * 
	 * @return		returns the social security number of the customer
	 */
	public String GetSSN()
	{
		return this.SSN;
	}
	
	/**
	 * Method for setting customer information, 
	 * yet to be implemented, parameter information
	 * and return information missing at the moment.
	 */
	public void SetCustomerInformation()
	{
		
	}
	
}
