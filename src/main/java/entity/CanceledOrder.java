package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "canceled_order")
@Getter
@Setter
public class CanceledOrder {

    @Id
    @GeneratedValue
    protected Integer id;

    private Instant date;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private DietOrder dietOrder;

}
