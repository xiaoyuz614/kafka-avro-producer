
/**
 * Utility Class to generate random instances of {@link Customer}
 * 
 * @author Affan Hasan
 */
public class CustomerGenerator {

	/**
	 * Default private constructor
	 */
	private CustomerGenerator() {
		
	}
	
	private static long counter = 0l;
	
	public static Customer getNext() {
		final String customer = "customer" + counter++;
		return new Customer(customer, customer + "@virtualcompany.com", "+92 0333 325179" + customer);
	}
}
