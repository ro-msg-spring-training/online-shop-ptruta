package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="orders")
public class Order {
    @Id
    private Integer id;
    @ManyToOne
    private Location shippedForm;
    @ManyToOne
    private Customer customer;
    private LocalDateTime localDateTime;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreetAddress;
}
