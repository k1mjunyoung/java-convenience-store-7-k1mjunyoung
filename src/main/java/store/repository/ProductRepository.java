package store.repository;

import store.model.Products;
import store.reader.ProductReader;

public class ProductRepository {
    public Products findAll() {
        return ProductReader.roadStock();
    }
}
