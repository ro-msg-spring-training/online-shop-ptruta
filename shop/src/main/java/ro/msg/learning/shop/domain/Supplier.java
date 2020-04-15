package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Supplier {
    @Id
    private Integer id;
    private String name;
//    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
//    private List<Product> products = new ArrayList<>();
}
