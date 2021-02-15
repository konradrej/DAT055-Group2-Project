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
	public String GetName(){
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
	 * Method for setting the name of the customer
	 * 
	 * @param		the name of the customer
	 */
	public void SetName(String name){
		this.name = name;
	}
	
	/**
	 * Method for setting the phone number of the customer
	 * 
	 * @param		the phone number of the customer
	 */
	public void SetPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Method for setting the social security number of the customer
	 * 
	 * @param		the social security number of the customer
	 */
	public void SetSSN(String SSN)
	{
		this.SSN = SSN;
	}
}