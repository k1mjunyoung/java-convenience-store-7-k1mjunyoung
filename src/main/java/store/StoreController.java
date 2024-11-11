package store;

import store.model.Products;
import store.model.Promotions;
import store.service.StoreService;
import store.utils.ErrorType;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {
    private final OutputView outputView;
    private final InputView inputView;
    private final StoreService storeService;

    public StoreController(OutputView outputView,
                           InputView inputView,
                           StoreService storeService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.storeService = storeService;
    }

    public void run() {
        outputView.printInitialMessage();

        Products stock = storeService.getProducts();
        Promotions promotions = storeService.getPromotions();
        
//        Products validStock = storeService.removeExpiredPromotionalProduct(stock, promotions);

        outputView.printStockMesage(stock);

        String item = inputView.readItem();
        Products order = storeService.getOrder(item);

        ErrorType checkStock = storeService.checkStock(stock, order);
        if (!checkStock.equals(ErrorType.VALID_INPUT)) {
            outputView.printError(checkStock.getMessage());
        }
    }
}
