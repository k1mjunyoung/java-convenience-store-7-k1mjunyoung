package store.service;

import camp.nextstep.edu.missionutils.Console;
import store.model.*;
import store.utils.ErrorType;

public class StoreService {
    private static final String NO_PROMOTION_MESSAGE = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n";
    private static final String ADD_PROMOTION_MESSAGE = "추가 %d개 상품을 가져가셔야 프로모션 혜택을 받습니다. 추가하시겠습니까? (Y/N)\n";
    private static final String TOTAL_AMOUNT_WITHOUT_PROMOTION = "%s x %d, 총 가격: %d원 (프로모션 적용: %d개 무료)\n";
    private static final String TOTAL_AMOUNT_WITH_PROMOTION = "%s x %d, 총 가격: %d원 (프로모션 없음)\n";
    private static final String MEMBERSHIP_MESSAGE = "멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String TOTAL_AMOUNT = "총 결제 금액: %d원\n";
    private static final String CONTINUE_MESSAGE = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";
    private static final String POSITIVE_ANSWER = "Y";
    private static final String NEGATIVE_ANSWER = "N";
    private static final String RECEIPT_START = "==============W 편의점================";

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
            Products foundProducts = productService.getProducts(orderedProduct.getName());
            if (foundProducts.getProducts().isEmpty()) {
                return ErrorType.PRODUCT_DOSE_NOT_EXIST;
            }

            Integer stockQuantity = productService.getQuantity(orderedProduct);
            if (orderedProduct.getQuantity() > stockQuantity) {
                return ErrorType.PRODUCT_IS_NOT_ENOUGH;
            }
        }

        return ErrorType.VALID_INPUT;
    }

    public Promotions getValidPromotions() {
        return promotionService.getValidPromotions();
    }

    public Boolean printReceipt(Products items) {
        int totalAmount = 0;
        System.out.println(RECEIPT_START);

        for (Product item : items.getProducts()) {
            String productName = item.getName();
            int quantity = item.getQuantity();
            Product product = productService.getPromotionProducts(productName);

            Promotion promotion = promotionService.getPromotion(product.getPromotion());
            int itemTotal = 0;

            if (promotion != null && promotionService.isValidPromotion(promotion)) {
                int eligibleSets = quantity / promotion.getBuy();
                int freeItems = eligibleSets * promotion.getGet();
                int actualQuantity = quantity - freeItems;

                if (actualQuantity > product.getQuantity()) {
                    System.out.printf(NO_PROMOTION_MESSAGE, productName, actualQuantity - product.getQuantity());
                    String buyAtFullPrice = Console.readLine();
                    if (buyAtFullPrice.equalsIgnoreCase(POSITIVE_ANSWER)) {
                        actualQuantity = product.getQuantity();
                    }
                    if (buyAtFullPrice.equalsIgnoreCase(NEGATIVE_ANSWER)){
                        actualQuantity = product.getQuantity();
                        quantity -= (actualQuantity - product.getQuantity());
                    }
                }

                System.out.printf(ADD_PROMOTION_MESSAGE, promotion.getBuy() - (quantity % promotion.getBuy()));
                String addMoreItems = Console.readLine();
                if (addMoreItems.equalsIgnoreCase(POSITIVE_ANSWER)) {
                    quantity += (promotion.getBuy() - (quantity % promotion.getBuy()));
                }

                itemTotal = actualQuantity * product.getPrice();
                System.out.printf(TOTAL_AMOUNT_WITHOUT_PROMOTION, productName, quantity, itemTotal, freeItems);
            } else {
                itemTotal = quantity * product.getPrice();
                System.out.printf(TOTAL_AMOUNT_WITH_PROMOTION, productName, quantity, itemTotal);
            }

            totalAmount += itemTotal;
        }

        System.out.println(MEMBERSHIP_MESSAGE);
        String applyMembershipDiscount = Console.readLine();
        if (applyMembershipDiscount.equalsIgnoreCase(POSITIVE_ANSWER)) {
            totalAmount *= 0.7;  // 예를 들어 10% 할인
        }

        System.out.printf(TOTAL_AMOUNT, totalAmount);

        System.out.println(CONTINUE_MESSAGE);
        String additionalPurchase = Console.readLine();
        if (additionalPurchase.equalsIgnoreCase(POSITIVE_ANSWER)) {
            return true;
        }

        return false;
    }


    /*public void checkPromotion(Promotions promotions, Products order) {
        Products cart = new Products();

        for (Product orderedProduct : order.getProducts()) {
            Promotion promotionOfOrderedProduct = promotionService.getPromotion(orderedProduct.getPromotion());

            if (promotionService.isValidPromotion(promotionOfOrderedProduct)) {
                Product stockProduct = productService.getPromotionProducts(orderedProduct.getName());
                Integer stockProductQuantity = stockProduct.getQuantity();

                if (orderedProduct.getQuantity() <= stockProductQuantity) {

                }
            }

            if (matchedProduct != null) {

            }
        }
    }*/

    /*@Deprecated
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
    }*/
}
