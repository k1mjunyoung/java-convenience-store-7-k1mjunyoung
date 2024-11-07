package store.model;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private List<Product> products;

    public Products() {
        this.products = new ArrayList<>();
    }

    public Products(List<Product> stock) {
        this.products = stock;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Products{" +
                "products=" + products +
                '}';
    }
}
