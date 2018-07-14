package vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class PhoneNumber {
    @Column(name = "phone_number")
    private String phoneNumber;

    public PhoneNumber() {
    }

    public static PhoneNumber of(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Phone cannot be null");
        }

        return new PhoneNumber(value);
    }

    private PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
