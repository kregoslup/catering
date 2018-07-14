package dao;

import entity.Diet;
import entity.Promotion;

import javax.persistence.TypedQuery;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class PromotionDAO extends BaseDAO implements IPromotionDAO {
    @Override
    public Promotion savePromotion(Promotion promotion) {
        return save(promotion);
    }

    @Override
    public Promotion fetchPromotion(int id) {
        return executeInTransaction(session -> session.get(Promotion.class, id));
    }

    @Override
    public Set<Promotion> fetchValidPromotions(Diet diet) {
        return executeInTransaction(session -> {
            TypedQuery<Promotion> query = session.createQuery(
                    "select p from Promotion p where " +
                            "p.start < :currentDate and p.end > :currentDate",
                    Promotion.class
            );
//            query.setParameter("diet", diet);
            query.setParameter("currentDate", Instant.now());
            return new HashSet<>(query.getResultList());
        });
    }
}
