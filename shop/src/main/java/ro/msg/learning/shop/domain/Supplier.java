package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "supplier", schema = "SHOP_SCHEMA")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
