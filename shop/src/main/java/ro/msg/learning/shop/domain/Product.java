package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "product", schema = "SHOP_SCHEMA")
@Entity
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

    private Double weight;

    @ManyToOne
    private ProductCategory productCategory;

    @ManyToOne
    private Supplier supplier;

    private String imageUrl;
}
