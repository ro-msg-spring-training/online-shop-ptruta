package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@Table(name="orders", schema = "SHOP_SCHEMA")
@Builder
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
//    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
//    private List<OrderDetail> orderDetailList = new ArrayList<>();
}
