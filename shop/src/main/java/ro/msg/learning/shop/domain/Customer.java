package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Customer {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String emailAddress;
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    private List<Order> orders = new ArrayList<>();
}
