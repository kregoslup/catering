package dto;

import entity.Promotion;
import javafx.beans.property.SimpleStringProperty;

public class PromotionCombobox {
    private SimpleStringProperty name;

    private final int id;

    public PromotionCombobox(SimpleStringProperty name, int id) {
        this.name = name;
        this.id = id;
    }

    public static PromotionCombobox fromPromotionEntity(Promotion promotion) {
        return new PromotionCombobox(
                new SimpleStringProperty(promotion.getName()),
                promotion.getId()
        );
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name.getName();
    }
}
