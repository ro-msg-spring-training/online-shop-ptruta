package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Stock {
    @Id
    private Integer stock_id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Location location;
    private Integer quantity;
}
