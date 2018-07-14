package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee extends Person {

    public enum PersonType {
        COOK, DIETETICIAN
    }

    private LocalDate employmentDate;

    private String maidenName;

    private String specialization;

    private String education;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private PersonType type;

    public Employee() {
    }

    public Employee(String personalData, Instant birthDate, LocalDate employmentDate, String maidenName, String specialization, String education, Client client, PersonType type) {
        super(personalData, birthDate);
        this.employmentDate = employmentDate;
        this.maidenName = maidenName;
        this.specialization = specialization;
        this.education = education;
        this.client = client;
        this.type = type;
    }

    /**
     * Calculates salary for an employee
     */
    private void calculateSalary() {}

    /**
     * If employee is dietitian type, creates a diet.
     */
    private void createDiet() {

    }
}
