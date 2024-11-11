package store.service;

import java.time.LocalDate;
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

    public Promotions getValidPromotions() {
        return promotionRepository.findByDate(LocalDate.now());
    }
}
