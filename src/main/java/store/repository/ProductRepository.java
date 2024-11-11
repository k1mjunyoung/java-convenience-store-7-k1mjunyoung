package store.repository;

import store.model.Product;
import store.model.Products;
import store.reader.ProductReader;

public class ProductRepository {
    public Products findAll() {
        return ProductReader.roadStock();
    }

    public Products findByName(String name) {
        Products stock = ProductReader.roadStock();
        Products foundProducts = new Products();

        for (Product product : stock.getProducts()) {
            if (product.getName().equals(name)) {
                foundProducts.addProduct(product);
            }
        }

        return foundProducts;
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

    public Product findByNameAndPromotionIsNotNull(String name) {
        Products stock = ProductReader.roadStock();

        for (Product product : stock.getProducts()) {
            if (product.getName().equals(name) && !product.getPromotion().equals("null")) {
                return product;
            }
        }

        return null;
    }
}
