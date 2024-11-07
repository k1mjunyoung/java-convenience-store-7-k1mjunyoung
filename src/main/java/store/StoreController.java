package store;

import store.model.Products;
import store.model.Promotions;
import store.view.OutputView;

public class StoreController {
    private final OutputView outputView;
    private final StoreService storeService;

    public StoreController(OutputView outputView,
                           StoreService storeService) {
        this.outputView = outputView;
        this.storeService = storeService;
    }

    public void run() {
        outputView.printInitialMessage();

        Products stock = StockReader.roadStock();
        System.out.println(stock);

        Promotions promotions = PromotionReader.roadPromotions();
        System.out.println(promotions);
        
        Products validStock = storeService.removeExpiredPromotionalProduct(stock, promotions);
        System.out.println("validStock = " + validStock);
    }
}
