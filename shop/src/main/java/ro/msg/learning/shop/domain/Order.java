package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Location shippedForm;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    private LocalDateTime localDateTime;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreetAddress;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList = new ArrayList<>();
}
