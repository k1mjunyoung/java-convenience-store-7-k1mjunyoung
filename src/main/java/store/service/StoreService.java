package store.service;

import java.util.ArrayList;
import java.util.List;
import store.model.Product;
import store.model.Products;
import store.model.Promotion;
import store.model.Promotions;
import store.utils.ErrorType;

public class StoreService {
    private final ProductService productService;
    private final PromotionService promotionService;

    public StoreService(ProductService productService, PromotionService promotionService) {
        this.productService = productService;
        this.promotionService = promotionService;
    }

    public Products getProducts() {
        return productService.getProducts();
    }

    public Promotions getPromotions() {
        return promotionService.getPromotions();
    }

    public Products getOrder(String item) {
        return productService.convertInputToProducts(item);
    }

    public ErrorType checkStock(Products stock, Products order) {
        for (Product orderedProduct : order.getProducts()) {
            if (productService.getProduct(orderedProduct.getName()) == null) {
                return ErrorType.PRODUCT_DOSE_NOT_EXIST;
            }

            Integer stockQuantity = productService.getQuantity(orderedProduct);
            if (orderedProduct.getQuantity() > stockQuantity) {
                return ErrorType.PRODUCT_IS_NOT_ENOUGH;
            }
        }

        return ErrorType.VALID_INPUT;
    }

    @Deprecated
    public Products removeExpiredPromotionalProduct(Products products, Promotions promotions) {
        Products validProducts = new Products();
        List<Promotion> validPromotions = promotions.getValidPromotions();

        List<String> validPromoionNames = new ArrayList<>();
        for (Promotion promotion : validPromotions) {
            validPromoionNames.add(promotion.getName());
        }

        for (Product product : products.getProducts()) {
            if (validPromoionNames.contains(product.getPromotion())) {
                validProducts.getProducts().remove(product);
            }
        }

        return validProducts;
    }
}
