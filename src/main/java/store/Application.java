package store;

import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.service.ProductService;
import store.service.PromotionService;
import store.service.StoreService;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        StoreController storeController = new StoreController(
                new OutputView(),
                new InputView(),
                new StoreService(new ProductService(new ProductRepository()),
                        new PromotionService(new PromotionRepository()))
        );

        storeController.run();
    }
}
