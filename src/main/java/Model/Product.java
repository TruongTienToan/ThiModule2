package Model;

import java.util.List;
import java.util.Objects;

public class Product {
    private Long idProduct;
    private String name;
    private Double price;
    private Double quantity;
    private String description;
    public List<Product> products;

    public Product() {

    }

    public Product(Long idProduct, String name, Double price, Double quantity, String description, List<Product> products) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.products = products;
    }

    public Product(Long idProduct, String name, Double price, Double quantity, String description) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static Product parseProduct(String rawProduct) {
        String[] array = rawProduct.split(",");
        Product product = new Product();
        product.setIdProduct(Long.parseLong(array[0]));
        product.setName(array[1]);
        product.setPrice(Double.parseDouble(array[2]));
        product.setQuantity(Double.parseDouble(array[3]));
        product.setDescription(array[4]);
        return product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, name, price, quantity, description, products);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(idProduct, product.idProduct) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(quantity, product.quantity) && Objects.equals(description, product.description) && Objects.equals(products, product.products);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",
                idProduct,
                name,
                price,
                quantity,
                description);
    }
}
