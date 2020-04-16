package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "product_category", schema = "SHOP_SCHEMA")
@Entity
@EqualsAndHashCode
public class ProductCategory {
@Id
private Integer id;
private String name;
private String description;
//@OneToMany(mappedBy = "product_category", cascade = CascadeType.ALL)
//private List<Product> products = new ArrayList<>();
}