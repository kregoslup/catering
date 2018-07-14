package dao;

import entity.Diet;
import entity.Meal;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

public class DietDAO extends BaseDAO implements IDietDAO {

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Diet> fetchDiets() {
        return executeInTransaction(session -> {
            TypedQuery<Diet> query = session.createQuery("select d from Diet d", Diet.class);
            return new HashSet<>(query.getResultList());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diet saveDiet(Diet diet) {
        return save(diet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diet fetchDiet(int id) {
        return executeInTransaction(session -> session.get(Diet.class, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Meal> fetchMeals() {
        return executeInTransaction(session -> {
            TypedQuery<Meal> query = session.createQuery("select m from Meal m", Meal.class);
            return new HashSet<>(query.getResultList());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Meal saveMeal(Meal meal) {
        return save(meal);
    }
}
