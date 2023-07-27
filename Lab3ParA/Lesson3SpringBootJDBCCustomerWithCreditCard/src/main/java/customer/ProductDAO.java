package customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO {
    /*
    save() findByProductNumber() getAllProducts() findByProductName() removeProduct
    Saves a product in the database
    Return the product with this productNumber Return the list with all products
    Return the list with products with this name Remove the product with the given productNumber

     */

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public void clearDB(){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE from cs544_trianingdb.products",namedParameters);

    }

    public void save(Product product){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", product.getProductNumber());
        namedParameters.put("name", product.getName());
        namedParameters.put("price", product.getPrice());
        jdbcTemplate.update("INSERT INTO cs544_trianingdb.products VALUES ( :productNumber, :name, :price)",namedParameters);
    }

    public Product findByProductNumber(int productNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM cs544_trianingdb.products WHERE "
                        + "product_number =:productNumber ",
                namedParameters,
                (rs, rowNum) -> new Product( rs.getInt("product_number"),
                        rs.getString("name"),
                        rs.getDouble("price")));

        return product;
    }

    public List<Product> getAllProducts(){
        List<Product> products = jdbcTemplate.query("SELECT * FROM cs544_trianingdb.products",
                (rs, rowNum) -> new Product( rs.getInt("product_number"),
                        rs.getString("name"),
                        rs.getDouble("price")));
        return products;
    }

    public void removeProduct(int productNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        jdbcTemplate.update("DELETE FROM cs544_trianingdb.products WHERE product_number =:productNumber",namedParameters);
    }
}
