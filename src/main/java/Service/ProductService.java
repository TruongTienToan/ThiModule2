package Service;

import Model.Product;
import Ultitils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public final static String PATH = "E:\\CODEGYM\\Module2\\ThiModule2\\src\\product.csv";
    private static ProductService instance;

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public static List<Product> findAll() {
        List<Product> listProduct = new ArrayList<>();
        List<String> records = CSVUtils.readFile(PATH);
        for (String record : records) {
            listProduct.add(Product.parseProduct(record));
        }
        return listProduct;
    }

    public void addProduct(Product product) {
        List<Product> products = findAll();
        products.add(product);
        CSVUtils.writeFile(PATH, products);
    }

    public void update(Product newProduct) {
        List<Product> productList = findAll();
        for (Product product : productList) {
            if (newProduct.getIdProduct().equals(product.getIdProduct())) {
                String name = newProduct.getName();
                if (name != null && !name.isEmpty()) {
                    product.setName(newProduct.getName());
                }
                Double quantity = newProduct.getQuantity();
                if (quantity != null) {
                    product.setQuantity(quantity);
                }
                Double price = newProduct.getPrice();
                if (price != null) {
                    product.setPrice(price);
                }
                String description = newProduct.getDescription();
                if (description != null && !description.isEmpty()) {
                    product.setDescription(newProduct.getDescription());
                }
                CSVUtils.writeFile(PATH, productList);
                break;
            }
        }
    }

    public void remove(Long idProduct) {
        List<Product> products = findAll();
        products.removeIf(id -> id.getIdProduct().equals(idProduct));
        CSVUtils.writeFile(PATH, products);
    }

    public Product findByID(Long idProduct) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getIdProduct().equals(idProduct)) {
                return product;
            }
        }
        return null;
    }

    public boolean existId(Long idProduct) {
        return findByID(idProduct) != null;
    }
}
