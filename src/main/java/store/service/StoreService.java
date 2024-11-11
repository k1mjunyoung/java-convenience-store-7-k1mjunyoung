package store.service;

import camp.nextstep.edu.missionutils.Console;
import store.model.*;
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

    public void printReceipt(Products items) {
        int totalAmount = 0;
        System.out.println("----- 영수증 -----");

        for (Product item : items.getProducts()) {
            String productName = item.getName();
            int quantity = item.getQuantity();
            Product product = productService.getPromotionProducts(productName);

            /*if (product == null) {
                System.out.println(productName + " 상품이 없습니다.");
                continue;
            }*/

            Promotion promotion = promotionService.getPromotion(product.getPromotion());
            int itemTotal = 0;

            if (promotion != null && promotionService.isValidPromotion(promotion)) {
                int eligibleSets = quantity / promotion.getBuy();
                int freeItems = eligibleSets * promotion.getGet();
                int actualQuantity = quantity - freeItems;

                if (actualQuantity > product.getQuantity()) {
                    System.out.printf("%s의 재고 부족. %d개는 정가로 구매해야 합니다.\n", productName, actualQuantity - product.getQuantity());
                    System.out.println("정가로 구매하시겠습니까? (Y/N)");
                    String buyAtFullPrice = Console.readLine();
                    if (buyAtFullPrice.equalsIgnoreCase("Y")) {
                        actualQuantity = product.getQuantity();
                    } else {
                        actualQuantity = product.getQuantity();
                        quantity -= (actualQuantity - product.getQuantity());
                    }
                }

                System.out.printf("추가 %d개 상품을 가져가셔야 프로모션 혜택을 받습니다. 추가하시겠습니까? (Y/N)\n", promotion.getBuy() - (quantity % promotion.getBuy()));
                String addMoreItems = Console.readLine();
                if (addMoreItems.equalsIgnoreCase("Y")) {
                    quantity += (promotion.getBuy() - (quantity % promotion.getBuy()));
                }

                itemTotal = actualQuantity * product.getPrice();
                System.out.printf("%s x %d, 총 가격: %d원 (프로모션 적용: %d개 무료)\n", productName, quantity, itemTotal, freeItems);
            } else {
                itemTotal = quantity * product.getPrice();
                System.out.printf("%s x %d, 총 가격: %d원 (프로모션 없음)\n", productName, quantity, itemTotal);
            }

            totalAmount += itemTotal;
        }

        System.out.println("멤버십 할인을 적용하시겠습니까? (Y/N)");
        String applyMembershipDiscount = Console.readLine();
        if (applyMembershipDiscount.equalsIgnoreCase("Y")) {
            totalAmount *= 0.9;  // 예를 들어 10% 할인
        }

        System.out.println("총 결제 금액: " + totalAmount + "원");

        System.out.println("추가 구매를 진행하시겠습니까? (Y/N)");
        String additionalPurchase = Console.readLine();
        if (additionalPurchase.equalsIgnoreCase("Y")) {
            System.out.println("재고가 업데이트된 상품 목록을 확인하고 추가로 구매를 진행하세요.");
            // 추가 구매 로직 추가 가능
        } else {
            System.out.println("구매를 종료합니다.");
        }
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
