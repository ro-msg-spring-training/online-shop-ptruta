package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "order_detail", schema = "SHOP_SCHEMA")
@Entity
@EqualsAndHashCode
@Builder
public class OrderDetail {
    @EmbeddedId
    private OrderDetailKey id;
    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
}
