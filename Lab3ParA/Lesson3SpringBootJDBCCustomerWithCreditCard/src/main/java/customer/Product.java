package customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    private int productNumber;
    private String name;

    private double price;

    public Product(int productNumber, String name, double price) {
        this.productNumber = productNumber;
        this.name = name;

        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNumber=" + productNumber +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public int getProductNumber() {
        return productNumber;
    }

    public String getName() {
        return name;
    }



    public double getPrice() { return price; }
}
