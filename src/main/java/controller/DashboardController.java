package controller;

import dao.IOrderDAO;
import dao.OrderDAO;
import dto.OrderTable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import session.Session;
import util.StageUtil;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashboardController implements Initializable {

    @FXML public TableView<OrderTable> ordersTable;

    @FXML public TableColumn<OrderTable, String> orderNameColumn;

    @FXML public TableColumn<OrderTable, String> orderStatusColumn;

    private ObservableList<OrderTable> data;

    private IOrderDAO orderDAO = new OrderDAO();

    private final String NEXT_STAGE = "new_order.fxml";

    /**
     * @param location
     * @param resources
     * Initializes controller
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = FXCollections.observableArrayList(
                orderDAO.fetchOrders(Session.getInstance().getClient())
                        .stream()
                        .map(OrderTable::fromEntity)
                        .collect(Collectors.toList())
        );

        orderNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        orderStatusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
        ordersTable.setItems(data);
    }

    /**
     * @param actionEvent
     * Switches to creating new order view.
     */
    public void newOrder(ActionEvent actionEvent) {
        StageUtil.changeStage(NEXT_STAGE, ordersTable, this);
    }

    /**
     * @param actionEvent
     * Closes application
     */
    public void close(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
