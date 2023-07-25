package customers;

import org.springframework.stereotype.Component;

@Component
public class CustomerService implements ICustomerService {
//	ICustomerDAO customerDAO = new CustomerDAO();
//	IEmailSender emailSender = new EmailSender();
	private ICustomerDAO customerDAO;
	private IEmailSender emailSender;


	/* constructor injection for customerDAO and emailSender
	 to use constructor injection, comment out the two lines in the spring config file
	 and uncomment the following constructor
	 */
//	public CustomerService(ICustomerDAO customerDAO, IEmailSender emailSender) {
//		super();
//
//		ICustomerDAO customerDAO2 = new CustomerDAO();
//		IEmailSender emailSender2 = new EmailSender();
//		this.customerDAO = customerDAO2;
//		this.emailSender = emailSender2;
//	}


	 //setter injection for customerDAO
	public void setCustomerDAO(ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	// setter injection for emailSender
	public void setEmailSender(IEmailSender emailSender) {
		this.emailSender = emailSender;
	}

	public void addCustomer(String name, String email, String street,
			String city, String zip) {
		Customer customer = new Customer(name, email);
		Address address = new Address(street, city, zip);
		customer.setAddress(address);
		customerDAO.save(customer);
		emailSender.sendEmail(email, "Welcome " + name + " as a new customer");
	}
}
