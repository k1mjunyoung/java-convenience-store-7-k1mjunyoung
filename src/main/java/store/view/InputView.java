package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.utils.Constant;
import store.utils.ErrorType;
import store.utils.Validator;

public class InputView {
    private static final String READ_ITEM_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    public String readItem() {
        System.out.println(READ_ITEM_MESSAGE);

        while (true) {
            String input = Console.readLine();

            ErrorType validator = Validator.isValidItemInput(input);
            if (validator.equals(ErrorType.VALID_INPUT)) {
                return input;
            }

            printInputError(validator.getMessage());
        }
    }

    private void printInputError(String errorMessage) {
        System.out.println(Constant.ERROR_PREFIX + errorMessage);
    }
}
