package controller;

import dao.IOrderDAO;
import dao.IPromotionDAO;
import dao.OrderDAO;
import dao.PromotionDAO;
import dto.PromotionCombobox;
import entity.Diet;
import entity.DietOrder;
import entity.Promotion;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.reflect.FieldUtils;
import session.Session;
import util.AlertFactory;
import util.StageUtil;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SummaryController implements Initializable {

    @FXML public TextField discountText;

    @FXML public DatePicker startDateText;

    @FXML public ComboBox<PromotionCombobox> promotion;

    @FXML public TextField personalDataText;

    @FXML public TextField addressText;

    @FXML public TextField dietPurposeText;

    @FXML public TextField nameText;

    @FXML public TextField costText;

    private final Diet chosenDiet = Session.getInstance().getDiet();

    private final IOrderDAO orderDAO = new OrderDAO();

    private final IPromotionDAO promotionDAO = new PromotionDAO();

    private ObservableList<PromotionCombobox> data = FXCollections.observableArrayList();

    private final static String NEXT_STAGE = "orders.fxml";

    /**
     * @param url
     * @param resourceBundle
     * Initializes controller
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameText.setText(chosenDiet.getName());
        costText.setText(String.valueOf(chosenDiet.calculateDailyCost()));
        dietPurposeText.setText(chosenDiet.getObjective().toString());
        discountText.setText(String.valueOf(Session.getInstance().getClient().countDiscount()));
        addressText.setText(String.valueOf(Session.getInstance().getClient().getAddress()));
        personalDataText.setText(String.valueOf(Session.getInstance().getClient().getPersonalData()));

        data = FXCollections.observableArrayList(promotionDAO.fetchValidPromotions(Session.getInstance().getDiet())
                .stream()
                .map(PromotionCombobox::fromPromotionEntity)
                .collect(Collectors.toList()));
        promotion.setItems(data);
        promotion.setPromptText("Promocje");
        promotion.valueProperty().addListener(listener -> {
            discountText.setText(String.valueOf(promotionDAO.fetchPromotion(
                    promotion.getSelectionModel().getSelectedItem().getId()
            ).getDiscount()));
        });
    }

    private void createOrder() {
        DietOrder dietOrder = new DietOrder();
        dietOrder.setAddress(String.valueOf(Session.getInstance().getClient().getAddress()));
        dietOrder.setClient(Session.getInstance().getClient());
        dietOrder.setCost(chosenDiet.calculateDailyCost());
        dietOrder.setDate(Instant.now());
        dietOrder.setDiet(chosenDiet);

        int discount = Session.getInstance().getClient().countDiscount();
        if (promotion.getSelectionModel().getSelectedItem() != null) {
            discount += promotionDAO.fetchPromotion(
                    promotion.getSelectionModel().getSelectedItem().getId()
            ).getDiscount();
        }
        dietOrder.setDiscount(discount);
        dietOrder.setStatus(DietOrder.OrderStatus.NEW);
        dietOrder.setLength(DietOrder.OrderLength.CONSTANT);
        dietOrder.setStart(startDateText.getValue().atStartOfDay().toInstant(ZoneOffset.UTC));

        orderDAO.saveDiet(dietOrder);
    }

    /**
     * @param actionEvent
     * Adds order to database for further processing. Proceeds to dashboard view
     */
    public void accept(ActionEvent actionEvent) {
        if (startDateText.getValue() == null) {
            AlertFactory.showInfoAlert("Start date", "Please pick the starting date");
            return;
        }
        createOrder();
        AlertFactory.showInfoAlert("Order", "Thank you for your time.");
        StageUtil.changeStage(NEXT_STAGE, promotion, this);
    }

    /**
     * @param actionEvent
     * Exits application
     */
    public void cancel(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
