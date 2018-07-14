package controller;

import dao.DietDAO;
import dao.IDietDAO;
import dto.DietTable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import session.Session;
import util.AlertFactory;
import util.StageUtil;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class OrderController implements Initializable {

    private final IDietDAO dietDAO = new DietDAO();

    @FXML public TableView<DietTable> dietTable;

    @FXML public TableColumn<DietTable, String> dietNameColumn;

    @FXML public TableColumn<DietTable, String> dietPurposeColumn;

    @FXML public TableColumn<DietTable, String> dietCostColumn;

    @FXML public Button createButton;

    @FXML public TextField searchField;

    private ObservableList<DietTable> data = FXCollections.observableArrayList();

    private final String NEXT_STAGE_SUMMARY = "summary.fxml";

    private final String NEXT_STAGE_DIET = "create_diet.fxml";

    /**
     * @param actionEvent
     * Switches to creating diet view
     */
    public void createNew(ActionEvent actionEvent) {
        StageUtil.changeStage(NEXT_STAGE_DIET, createButton, this);
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
     * Accepts diet chosen by user and proceeds to order summary view
     */
    public void accept(ActionEvent actionEvent) {
        DietTable diet = dietTable.getSelectionModel().getSelectedItem();
        if (diet != null) {
            Session.getInstance().setDiet(dietDAO.fetchDiet(diet.getId()));
            StageUtil.changeStage(NEXT_STAGE_SUMMARY, createButton, this);
        } else {
            AlertFactory.showInfoAlert("Search", "Please choose a diet first");
        }
    }

    /**
     * @param actionEvent
     * Searches for diet. If found, proceeds to summary view.
     */
    public void searchDiet(ActionEvent actionEvent) {
        if (searchField.getText() != null && !searchField.getText().equals("")) {

            String dietSearch = searchField.getText();
            boolean exists = data.stream()
                    .anyMatch(x -> x.getName().equals(dietSearch));

            String infoMessage;
            if (exists) {
                infoMessage = "Diet exists, continuing";
                accept(actionEvent);
            } else {
                infoMessage = "Diet does not exist";
            }

            AlertFactory.showInfoAlert("Search", infoMessage);
        }
    }

    private void getDiets() {
        data = FXCollections.observableArrayList(
                dietDAO.fetchDiets()
                        .stream()
                        .map(DietTable::fromDietEntity)
                        .collect(Collectors.toList())
        );
    }

    /**
     * @param url
     * @param resourceBundle
     * Initializes controller
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dietNameColumn.setMinWidth(100);
        dietNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        dietPurposeColumn.setMinWidth(100);
        dietPurposeColumn.setCellValueFactory(
                new PropertyValueFactory<>("purpose")
        );

        dietCostColumn.setMinWidth(100);
        dietCostColumn.setCellValueFactory(
                new PropertyValueFactory<>("cost")
        );
        getDiets();
        dietTable.setItems(data);
    }
}
