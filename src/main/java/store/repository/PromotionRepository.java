package store.repository;

import java.time.LocalDate;
import store.model.Promotion;
import store.model.Promotions;
import store.reader.PromotionReader;

public class PromotionRepository {
    public Promotions findAll() {
        return PromotionReader.roadPromotions();
    }

    public Promotion findByName(String name) {
        Promotions promotions = PromotionReader.roadPromotions();
        for (Promotion promotion : promotions.getPromotions()) {
            if (promotion.getName().equals(name)) {
                return promotion;
            }
        }

        return null;
    }

    public Promotions findByDate(LocalDate date) {
        Promotions validPromotions = new Promotions();
        Promotions promotions = PromotionReader.roadPromotions();

        for (Promotion promotion : promotions.getPromotions()) {
            if (date.isAfter(promotion.getStartDate()) && date.isBefore(promotion.getEndDate())) {
                validPromotions.addPromotion(promotion);
            }
        }

        return validPromotions;
    }
}
