package dto;

import entity.Diet;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DietTable {

    private final int id;

    private SimpleStringProperty name;

    private SimpleStringProperty purpose;

    private IntegerProperty cost;

    public DietTable(int id, SimpleStringProperty name, SimpleStringProperty purpose, IntegerProperty cost) {
        this.id = id;
        this.name = name;
        this.purpose = purpose;
        this.cost = cost;
    }

    public static DietTable fromDietEntity(Diet diet) {
        return new DietTable(
                diet.getId(),
                new SimpleStringProperty(diet.getName()),
                new SimpleStringProperty(diet.getObjective().toString()),
                new SimpleIntegerProperty(diet.calculateDailyCost())
        );
    }

    public int getId() {
        return id;
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

    public String getPurpose() {
        return purpose.get();
    }

    public SimpleStringProperty purposeProperty() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose.set(purpose);
    }

    public int getCost() {
        return cost.get();
    }

    public IntegerProperty costProperty() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost.set(cost);
    }
}
