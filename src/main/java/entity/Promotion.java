package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "promotion")
@Getter
@Setter
public class Promotion {

    @Id
    @GeneratedValue
    protected Integer id;

    private String name;

    private Instant start;

    private Instant end;

    private int discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_id")
    private Diet diet;

    public Promotion() {
    }

    public Promotion(Instant start, Instant end, int discount, Diet diet, String name) {
        this.start = start;
        this.end = end;
        this.discount = discount;
        this.diet = diet;
        this.name = name;
    }
}
