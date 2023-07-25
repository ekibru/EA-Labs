package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public ICustomerDAO customerDAO() {
        return new CustomerDAO();
    }

    @Bean
    public IEmailSender emailSender() {
        return new EmailSender();
    }

    @Bean
    public ICustomerService customerService() {
        return new CustomerService(customerDAO(), emailSender());
    }

    @Bean
    public ILogger logger() {
        return new Logger();
    }



}
