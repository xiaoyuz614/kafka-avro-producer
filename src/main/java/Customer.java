
/**
 * Customer class definition
 * 
 * @author Affan Hasan
 */
public class Customer {
	
	private String name;
	private String email;
	private String contactNumber;
	
	/**
	 * Default object constructor
	 * 
	 * @param name name
	 * @param email email address
	 * @param contactNumber customer phone number
	 */
	public Customer(final String name, final String email, final String contactNumber) {
		this.name = name;
		this.email = email;
		this.contactNumber = contactNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return String.format("[ Name: %s | Email Address: %s | Phone: %s ]", name, email, contactNumber);
	}
}
