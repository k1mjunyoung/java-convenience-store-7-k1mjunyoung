package store.model;

import java.util.ArrayList;
import java.util.List;

public class Promotions {
    private List<Promotion> promotions;

    public Promotions() {
        promotions = new ArrayList<>();
    }

    public Promotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void addPromotion(Promotion promotion) {
        this.promotions.add(promotion);
    }

    public List<Promotion> getValidPromotions() {
        List<Promotion> validPromotions = new ArrayList<>();

        for (Promotion promotion : this.promotions) {
            if (promotion.isValidPromotion()) {
                validPromotions.add(promotion);
            }
        }

        return validPromotions;
    }

    @Override
    public String toString() {
        return "Promotions{" +
                "promotions=" + promotions +
                '}';
    }
}
