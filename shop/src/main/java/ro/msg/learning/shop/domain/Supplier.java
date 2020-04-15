package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Supplier {
    @Id
    private Integer id;
    private String name;;
}
