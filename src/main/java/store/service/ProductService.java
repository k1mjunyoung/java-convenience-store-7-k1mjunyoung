package store.service;

import store.model.Products;
import store.repository.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Products getProducts() {
        return productRepository.findAll();
    }
}
