package store.service;

import java.util.Arrays;
import java.util.List;
import store.model.Product;
import store.model.Products;
import store.repository.ProductRepository;
import store.utils.Constant;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Products getProducts() {
        return productRepository.findAll();
    }

    public Products convertInputToProducts(String input) {
        Products products = new Products();
        List<String> itemInputs = Arrays.stream(input.split(Constant.COMMA)).toList();

        for (String itemInput : itemInputs) {
            String tempInput = itemInput.replaceAll(Constant.REGEX_BRAKETS_WITH_SPACE, Constant.EMPTY_STRING);

            List<String> splitedItemInputs = Arrays.stream(tempInput.split(Constant.DASH)).toList();

            Product product = new Product(splitedItemInputs.getFirst(), Integer.valueOf(splitedItemInputs.getLast()));

            products.addProduct(product);
        }

        return products;
    }

    public Product getProduct(String name) {
        return productRepository.findByName(name);
    }

    public Integer getQuantity(Product product) {
        return productRepository.findQuantityByName(product.getName());
    }
}
