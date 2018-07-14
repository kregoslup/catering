package util;

import dao.*;
import entity.*;
import session.Session;
import vo.PhoneNumber;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SeedData {

    private static final PersonDAO personDAO = new PersonDAO();

    private static final IDietDAO dietDAO = new DietDAO();

    private static final IPromotionDAO promotionDAO = new PromotionDAO();

    private static void seedClient() {
        personDAO.addClient(new Client(
                "Personal data 1",
                Instant.now(),
                PhoneNumber.of("123456"),
                "email@email.com",
                "Koszykowa 20/52",
                null
            ));

        personDAO.addEmployee(
                new Employee(
                        "Employee personal data 1",
                        Instant.now(),
                        LocalDate.now(),
                        "Maiden name 1",
                        "Kuchnia azjatycka",
                        null,
                        null,
                        Employee.PersonType.COOK
                )
        );
    }

    private static void seedMeal() {
        dietDAO.saveMeal(
                new Meal(
                        "Rosol",
                        20,
                        400
                )
        );
        dietDAO.saveMeal(
                new Meal(
                        "Mielone",
                        50,
                        1000
                )
        );
        dietDAO.saveMeal(
                new Meal(
                        "Pizza",
                        100,
                        800
                )
        );
    }

    private static void seedDiet() {
        dietDAO.saveDiet(
                new Diet(
                      "Dieta odchudzająca",
                      Diet.DietPurpose.LOSE,
                      dietDAO.fetchMeals()
                )
        );
        dietDAO.saveDiet(
                new Diet(
                        "Dieta na tycie",
                        Diet.DietPurpose.GAIN,
                        dietDAO.fetchMeals()
                )
        );
    }

    private static void seedPromotions() {
        promotionDAO.savePromotion(
                new Promotion(
                        Instant.now().minus(2, ChronoUnit.DAYS),
                        Instant.now().plus(2, ChronoUnit.DAYS),
                        30,
                        dietDAO.fetchDiets().stream().findFirst().get(),
                        "Super promocja"
                )
        );
        promotionDAO.savePromotion(
                new Promotion(
                        Instant.now().plus(1, ChronoUnit.DAYS),
                        Instant.now().plus(5, ChronoUnit.DAYS),
                        60,
                        dietDAO.fetchDiets().stream().findFirst().get(),
                        "Ekstra promocja"
                )
        );
        promotionDAO.savePromotion(
                new Promotion(
                        Instant.now().minus(2, ChronoUnit.DAYS),
                        Instant.now().plus(2, ChronoUnit.DAYS),
                        40,
                        dietDAO.fetchDiets().stream().findAny().get(),
                        "Najlepsza promocja"
                )
        );
    }

    private static void seedSession() {
        Session.getInstance().setClient(personDAO.fetchMockClient());
    }

    /**
     * Seeds database with mocked input
     */
    public static void seedDatabase() {
        seedClient();
        seedMeal();
        seedDiet();
        seedPromotions();
        seedSession();
    }
}
