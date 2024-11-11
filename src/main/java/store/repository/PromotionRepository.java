package store.repository;

import store.model.Promotions;
import store.reader.PromotionReader;

public class PromotionRepository {
    public Promotions findAll() {
        return PromotionReader.roadPromotions();
    }
}
