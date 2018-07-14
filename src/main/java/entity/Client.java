package entity;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vo.PhoneNumber;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client extends Person {

    private final static Logger logger = LoggerFactory.getLogger(Client.class);

    @Embedded
    private PhoneNumber phoneNumber;

    private String email;

    private String address;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Employee employee;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<DietOrder> orders = new HashSet<>();

    public int countDiscount() {
        return 0;
    }

    public Client() {
    }

    public Client(String personalData, Instant birthDate, PhoneNumber phoneNumber, String email, String address, Employee employee) {
        super(personalData, birthDate);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.employee = employee;
    }

    /**
     * Contacts with dietitian
     */
    public void contactDietetitian() {
        logger.info("Contacting with dietetitian...");
        logger.info("Waiting for answer...");
        logger.info("Saving result of conversation...");
        logger.info("Done");
    }
}
