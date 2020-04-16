package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "order_detail", schema = "SHOP_SCHEMA")
@Entity
@EqualsAndHashCode
public class OrderDetail {
    @Id
    private Integer order_detail_id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private Integer quantity;
}
