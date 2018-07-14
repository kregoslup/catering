package controller;

import dao.DietDAO;
import dao.IDietDAO;
import entity.Diet;
import entity.Meal;
import org.apache.commons.lang3.StringUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import session.Session;
import util.AlertFactory;
import util.StageUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class DietController implements Initializable {

    @FXML public TextField nameText;

    @FXML public TextField costText;

    @FXML public ComboBox<String> variantCBox;

    @FXML public ComboBox<Diet.DietPurpose> objectiveCBox;

    private final IDietDAO dietDAO = new DietDAO();

    private final String NEXT_STAGE = "summary.fxml";

    /**
     * @param actionEvent
     * Button callback, accepts new diet parameter and switches to order summary view
     */
    public void accept(ActionEvent actionEvent) {
        if (StringUtils.isBlank(nameText.getCharacters()) || objectiveCBox.getSelectionModel().getSelectedItem() == null ||
                variantCBox.getSelectionModel().getSelectedItem() == null) {
            AlertFactory.showInfoAlert("Details", "Please fill in the details in order to continue");
        } else {
            Diet diet = createDiet();
            Session.getInstance().setDiet(diet);
            StageUtil.changeStage(NEXT_STAGE, nameText, this);
        }
    }

    private Diet createDiet() {
        Diet diet = new Diet();
        diet.setName(nameText.getText());
        diet.setObjective(objectiveCBox.getSelectionModel().getSelectedItem());
        diet.setMeals(dietDAO.fetchMeals());
        diet = dietDAO.saveDiet(diet);
        return diet;
    }

    /**
     * @param actionEvent
     * Exits application
     */
    public void cancel(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    /**
     * @param actionEvent
     * Mocks contacting with dietitian.
     */
    public void contact(ActionEvent actionEvent) {
        AlertFactory.showInfoAlert("Contact", "Contacting with dietitian...");

        Session.getInstance().getClient().contactDietetitian();
        if (variantCBox.getSelectionModel().getSelectedItem() != null) {
            costText.setText(String.valueOf(dietDAO.fetchMeals()
                    .stream()
                    .mapToInt(Meal::getCost)
                    .sum())
            );
        }
    }

    /**
     * @param url
     * @param resourceBundle
     * Initializes controller
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        objectiveCBox.getItems().addAll(
                Diet.DietPurpose.values()
        );
        variantCBox.getItems().addAll(
                // TODO: Dodac posilki powiązane z wariantami
                "Variant 1",
                "Variant 2",
                "Variant 3",
                "Variant 4"
        );
    }
}
