package customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class CustomerDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void clearDB() {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE from cs544_trianingdb.customer",namedParameters);
        jdbcTemplate.update("DELETE from cs544_trianingdb.creditcards",namedParameters);
    }

    public void save(Customer customer) {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("customernumber", customer.getCustomerNumber());
        namedParameters.put("name", customer.getName());
        namedParameters.put("email", customer.getEmail());
        namedParameters.put("phone", customer.getPhone());
        jdbcTemplate.update("INSERT INTO cs544_trianingdb.customer VALUES ( :customernumber, :name, :email, :phone)",namedParameters);

        // save creditcard
        Map<String,Object> namedParameterscc = new HashMap<String,Object>();
        namedParameterscc.put("customernumber", customer.getCustomerNumber());
        namedParameterscc.put("cardnumber", customer.getCreditCard().getCardNumber());
        namedParameterscc.put("type", customer.getCreditCard().getType());
        namedParameterscc.put("validationDate", customer.getCreditCard().getValidationDate());
        jdbcTemplate.update("INSERT INTO cs544_trianingdb.creditcards VALUES ( :customernumber,:cardnumber, :type, :validationDate)",namedParameterscc);
    }



    public Customer getCustomer(int customerNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("customerNumber", customerNumber);
        Customer customer = jdbcTemplate.queryForObject("SELECT * FROM cs544_trianingdb.customer WHERE "
                        + "customer_number =:customerNumber ",
                namedParameters,
                (rs, rowNum) -> new Customer( rs.getInt("customer_number"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")));

        CreditCard creditCard = getCreditCardForCustomer(customer.getCustomerNumber());
        customer.setCreditCard(creditCard);
        return customer;

    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM cs544_trianingdb.customer",
                new HashMap<String, Customer>(),
                (rs, rowNum) -> new Customer( rs.getInt("customer_number"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")));

        for (Customer customer: customers){
            CreditCard creditCard = getCreditCardForCustomer(customer.getCustomerNumber());
            customer.setCreditCard(creditCard);
        }
        return customers;
    }

    CreditCard getCreditCardForCustomer(int customerNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("customerNumber", customerNumber);
        CreditCard creditCard = jdbcTemplate.queryForObject("SELECT * FROM cs544_trianingdb.creditcards WHERE "
                        + "customer_number_customer_number =:customerNumber ",
                namedParameters,
                (rs, rowNum) -> new CreditCard( rs.getString("card_number"),
                        rs.getString("type"),
                        rs.getString("validation_date")));

        return creditCard;
    }
}
