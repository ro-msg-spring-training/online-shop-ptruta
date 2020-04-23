package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "product_category", schema = "SHOP_SCHEMA")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;
}