package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProductCategory {
@Id
@GeneratedValue
private Integer id;
private String name;
private String description;
@OneToMany(mappedBy = "product_category", cascade = CascadeType.ALL)
private List<Product> products = new ArrayList<>();
}