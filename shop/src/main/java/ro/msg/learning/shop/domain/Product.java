package ro.msg.learning.shop.domain;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
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
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private List<Stock> stocks = new ArrayList<>();
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private List<OrderDetail> orderDetails = new ArrayList<>();
}
