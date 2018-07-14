package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue
    protected Integer id;

    protected String personalData;

    protected Instant birthDate;

    public Person() {
    }

    public Person(String personalData, Instant birthDate) {
        this.personalData = personalData;
        this.birthDate = birthDate;
    }
}
