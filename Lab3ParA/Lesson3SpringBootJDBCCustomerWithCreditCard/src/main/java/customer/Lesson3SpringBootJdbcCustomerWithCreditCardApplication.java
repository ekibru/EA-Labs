package customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson3SpringBootJdbcCustomerWithCreditCardApplication implements CommandLineRunner {

	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private ProductDAO productDao;
	public static void main(String[] args) {
		SpringApplication.run(Lesson3SpringBootJdbcCustomerWithCreditCardApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		customerDao.clearDB();
//		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
//		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
//		customer.setCreditCard(creditCard);
//		customerDao.save(customer);
//		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
//		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
//		customer.setCreditCard(creditCard);
//		customerDao.save(customer);

		productDao.clearDB();
		Product product = new Product(1, "Laptop", 1000);
		productDao.save(product);
		product = new Product(2, "Desktop", 2000);
		productDao.save(product);
		product = new Product(3, "Tablet", 3000);
		productDao.save(product);


		System.out.println(customerDao.getCustomer(101));
		System.out.println(customerDao.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerDao.getAllCustomers());
		System.out.println("-----------All products ----------------");
		System.out.println(productDao.getAllProducts());
		System.out.println("-----------Product with id 2 ----------------");
		System.out.println(productDao.findByProductNumber(2));
		System.out.println("-----------Product with id 3 ----------------");
		System.out.println(productDao.findByProductNumber(3));

	}
}
