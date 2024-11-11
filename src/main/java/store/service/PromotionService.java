package store.service;

import java.time.LocalDate;
import store.model.Promotion;
import store.model.Promotions;
import store.repository.PromotionRepository;

public class PromotionService {
    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotions getPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion getPromotion(String name) {
        return promotionRepository.findByName(name);
    }

    public Promotions getValidPromotions() {
        return promotionRepository.findByDate(LocalDate.now());
    }

    public Boolean isValidPromotion(Promotion promotion) {
        Promotions promotions = getValidPromotions();

        if (promotions.getPromotions().contains(promotion)) {
            return true;
        }

        return false;
    }
}
