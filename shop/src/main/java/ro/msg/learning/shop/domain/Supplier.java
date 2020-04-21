package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "supplier", schema = "SHOP_SCHEMA")
@Entity
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    private Integer id;
    private String name;
//    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
//    private List<Product> products = new ArrayList<>();
}
