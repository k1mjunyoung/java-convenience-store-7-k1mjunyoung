package store.repository;

import store.model.Product;
import store.model.Products;
import store.reader.ProductReader;

public class ProductRepository {
    public Products findAll() {
        return ProductReader.roadStock();
    }

    public Product findByName(String name) {
        Products stock = ProductReader.roadStock();

        for (Product product : stock.getProducts()) {
            if (product.getName().equals(name)) {
                return product;
            }
        }

        return null;
    }

    public Integer findQuantityByName(String name) {
        Products stock = ProductReader.roadStock();
        Integer quantity = 0;

        for (Product product : stock.getProducts()) {
            if (product.getName().equals(name)) {
                quantity += product.getQuantity();
            }
        }

        return quantity;
    }
}
