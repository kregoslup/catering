package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meal")
@Getter
@Setter
public class Meal {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private int cost;

    private int calories;

    public Meal() {
    }

    public Meal(String name, int cost, int calories) {
        this.name = name;
        this.cost = cost;
        this.calories = calories;
    }
}
