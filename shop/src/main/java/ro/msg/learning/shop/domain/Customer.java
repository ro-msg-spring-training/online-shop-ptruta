package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Customer {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String emailAddress;
}
