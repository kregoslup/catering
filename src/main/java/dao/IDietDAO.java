package dao;

import entity.Diet;
import entity.Meal;

import java.util.Set;

public interface IDietDAO {

    /**
     * Fetches all diets from database
     * @return Set of unique diets
     */
    Set<Diet> fetchDiets();

    /**
     * Saves provided diet to database
     *
     * @param diet Diet entity to save
     * @return Saved diet entity
     */
    Diet saveDiet(Diet diet);

    /**
     *
     * Searches for a diet with provided id. May throw RuntimeException if not found
     *
     * @param id Id of the searched diet
     * @return Diet with id
     */
    Diet fetchDiet(int id);

    /**
     * Fetches all meals from database
     *
     * @return Unique set of all meals from database
     */
    Set<Meal> fetchMeals();

    /**
     * Saves meal in database
     *
     * @param meal Meal entity to save
     * @return Saved meal entity
     */
    Meal saveMeal(Meal meal);
}
