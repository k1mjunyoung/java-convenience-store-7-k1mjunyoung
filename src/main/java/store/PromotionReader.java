package store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import store.model.Promotion;
import store.model.Promotions;

public class PromotionReader {
    private static final String PROMOTIONS_PATH = "src/main/resources/promotions.md";

    public static Promotions roadPromotions() {
        Promotions promotions = new Promotions();

        try {
            BufferedReader br = new BufferedReader(new FileReader(PROMOTIONS_PATH));
            br.readLine();

            while (true) {
                String line = br.readLine();

                if (line == null) {
                    break;
                }

                Promotion promotion = convertToPromotion(line);
                promotions.addPromotion(promotion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return promotions;
    }

    public static Promotion convertToPromotion(String line) {
        List<String> splitedLine = Arrays.stream(line.split(",")).toList();

        //name,buy,get,start_date,end_date
        String name = splitedLine.get(0);
        Integer buy = Integer.valueOf(splitedLine.get(1));
        Integer get = Integer.valueOf(splitedLine.get(2));
        LocalDate start_date = LocalDate.parse(splitedLine.get(3));
        LocalDate end_date = LocalDate.parse(splitedLine.get(4));

        Promotion promotion = new Promotion(name, buy, get, start_date, end_date);

        return promotion;
    }
}
