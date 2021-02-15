import java.io.Serializable;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6060898922086321725L;
	private String name;
	private String phoneNumber;
	private String ssn;
	
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
		this.ssn = SSN;
	}
	
	/**
	 * Method for getting the name of the customer
	 * 
	 * @return		returns the name of the customer
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Method for getting the phone number of the customer
	 * 
	 * @return		returns the phone number of the customer
	 */
	public String getPhoneNumber()
	{
		return this.phoneNumber;
	}
	
	/**
	 * Method for getting the social security number of the customer
	 * 
	 * @return		returns the social security number of the customer
	 */
	public String getSSN()
	{
		return this.ssn;
	}
	
	/**
	 * Method for setting the name of the customer
	 * 
	 * @param		the name of the customer
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Method for setting the phone number of the customer
	 * 
	 * @param		the phone number of the customer
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Method for setting the social security number of the customer
	 * 
	 * @param		the social security number of the customer
	 */
	public void setSSN(String SSN)
	{
		this.ssn = SSN;
	}
}