package ro.msg.learning.shop.domain;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductCategory productCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;
    private String imageUrl;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Stock> stocks = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
