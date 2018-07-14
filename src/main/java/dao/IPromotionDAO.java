package dao;

import entity.Diet;
import entity.Promotion;

import java.util.Set;

public interface IPromotionDAO {
    /**
     * Saves promotion in database
     * 
     * @param promotion Promotion to save
     * @return Saved promotion
     */
    Promotion savePromotion(Promotion promotion);

    /**
     * Searches for promotion in database
     *
     * @param id Id of promotion
     * @return Promotion if found
     */
    Promotion fetchPromotion(int id);

    /**
     * Fetches valid promotion for a given diet.
     * Valid means current date is between start and end data.
     *
     * @param diet Diet which is owner of promotions
     * @return Set of valid promotions
     */
    Set<Promotion> fetchValidPromotions(Diet diet);
}
