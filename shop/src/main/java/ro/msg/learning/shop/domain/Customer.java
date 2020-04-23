package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "customer", schema = "SHOP_SCHEMA")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String userName;

    private String emailAddress;
}
