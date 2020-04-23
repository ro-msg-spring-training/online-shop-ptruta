package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "order", schema = "SHOP_SCHEMA")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
