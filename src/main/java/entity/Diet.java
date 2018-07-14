package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diet")
@Getter
@Setter
public class Diet {

    public enum DietPurpose {
        GAIN, LOSE
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private DietPurpose objective;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "diet_meal",
            joinColumns = { @JoinColumn(name = "diet_id") },
            inverseJoinColumns = { @JoinColumn(name = "meal_id") }
    )
    private Set<Meal> meals = new HashSet<>();

    @OneToMany(
            mappedBy = "diet",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Promotion> promotions = new HashSet<>();

    @OneToMany(
            mappedBy = "diet",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<DietOrder> orders = new HashSet<>();

    public Diet() {
    }

    public Diet(String name, DietPurpose objective, Set<Meal> meals) {
        this.name = name;
        this.objective = objective;
        this.meals = meals;
    }

    /**
     * Calculates daily calories intake of a diet based on meals associated with diet
     * @return Calculated daily calories intake of the diet
     */
    public int calculateCalories() {
        return meals.stream().mapToInt(Meal::getCalories).sum();
    }

    /**
     * Calculates daily cost of a diet based on meals associated with diet
     * @return Calculated daily cost of the diet
     */
    public int calculateDailyCost() {
        return meals.stream().mapToInt(Meal::getCost).sum();
    }
}
