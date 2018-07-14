package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "diet_order")
@Getter
@Setter
public class DietOrder {

    private static final transient int MAX_DISCOUNT = 50;

    public enum OrderStatus {
        NEW, PAID, PROCESSING, FINISHED, CANCELED
    }

    public enum OrderLength {
        CONSTANT, PERIODIC
    }

    @Id
    @GeneratedValue
    protected Integer id;

    private String address;

    private Instant date;

    private int cost;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private OrderLength length;

    private Instant start;

    private Instant end;

    private int discount;

    private int hour;

    private String receiveAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    public Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_id")
    public Diet diet;

    @OneToOne(mappedBy = "dietOrder", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public CanceledOrder canceledOrder;

    /**
     * Pays for order
     */
    public void pay() {

    }

    /**
     * Cancels order. Creates canceled order entity.
     */
    public void cancel() {

    }

    /**
     * Changes length of an order.
     */
    public void changeLength() {

    }

    public void changeReceiveType() {

    }
}
