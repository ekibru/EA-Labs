
package com.example;
import com.example.domain.Book;
import com.example.domain.Customer;
import com.example.repositories.BookRepo;
import com.example.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;


@SpringBootApplication

public class CustomerApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerrepository;
	@Autowired
	private BookRepo bookRepo;


	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerrepository.save(new Customer("Jack", "Bauer", "jack@acme.com"));
		customerrepository.save(new Customer("Chloe", "O'Brian", "chloe@acme.com"));
		customerrepository.save(new Customer("Kim", "Bauer", "kim@acme.com"));
		customerrepository.save(new Customer("David", "Palmer", "dpalmer@gmail.com"));
		customerrepository.save(new Customer("Michelle", "Dessler", "mich@hotmail.com"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerrepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();


		bookRepo.save(new Book(1, "The Great Gatsby", "978-3-16-148410-0", "F. Scott Fitzgerald", 10.99));
		bookRepo.save(new Book(2, "The Grapes of Wrath", "978-3-16-148410-1", "John Steinbeck", 12.99));
		bookRepo.save(new Book(3, "Nineteen Eighty-Four", "978-3-16-148410-2", "George Orwell", 14.99));
		bookRepo.save(new Book(4, "Ulysses", "978-3-16-148410-3", "James Joyce", 16.99));

		// fetch an individual customer by ID
		Optional<Customer> custOpt = customerrepository.findById(1L);
		Customer customer = custOpt.get();

		// fetch an individual book by ID
		Optional<Book> bookOpt = bookRepo.findById(1L);
		Book books = bookOpt.get();


		// fetch all books
		System.out.println("Books found with findAll():");
		System.out.println("-------------------------------");
		for (Book book : bookRepo.findAll()) {
			System.out.println(book);

		}












		// fetch customer by ID
		System.out.println("Customer found with findOne(1L):");
		System.out.println("--------------------------------");
		System.out.println(customer);
		System.out.println();

		// fetch book by ID
		System.out.println("Book found with findOne(1L):");
		System.out.println("-------------------------------");
		System.out.println(books);

	}
}
