package store.view;

import store.model.Product;
import store.model.Products;

public class OutputView {
    private static final String INITIAL_MESSAGE = "안녕하세요. W편의점입니다.";
    private static final String STOCK_MESSAGE = "현재 보유하고 있는 상품입니다.";
    private static final String PURCHASE_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String NEW_LINE = "\n";

    public void printInitialMessage() {
        System.out.println(INITIAL_MESSAGE);
    }

    public void printStockMesage(Products stock) {
        System.out.println(STOCK_MESSAGE + NEW_LINE);

        for (Product product : stock.getProducts()) {
            System.out.println(product.toMessage());
        }

        System.out.print(NEW_LINE);
    }

    public void printPurchaseMesage() {
        System.out.println(PURCHASE_MESSAGE);
    }
}
