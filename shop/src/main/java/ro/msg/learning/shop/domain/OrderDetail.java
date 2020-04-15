package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class OrderDetail {
    @Id
    private Integer order_detail_id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private Integer quantity;
}
