package dto;

import entity.DietOrder;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

public class OrderTable {
    private SimpleStringProperty name;

    private SimpleStringProperty status;

    public OrderTable(SimpleStringProperty name, SimpleStringProperty status) {
        this.name = name;
        this.status = status;
    }

    public static OrderTable fromEntity(DietOrder order) {
        return new OrderTable(
                new SimpleStringProperty(order.getAddress()),
                new SimpleStringProperty(order.getStatus().toString())
        );
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
