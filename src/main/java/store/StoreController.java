package store;

import store.model.Products;
import store.model.Promotions;
import store.view.OutputView;

public class StoreController {
    private final OutputView outputView;

    public StoreController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void run() {
        outputView.printInitialMessage();

        Products stock = StockReader.roadStock();
        System.out.println(stock);

        Promotions promotions = PromotionReader.roadPromotions();
        System.out.println(promotions);
    }
}
