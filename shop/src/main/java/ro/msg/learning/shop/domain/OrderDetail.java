package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "order_detail", schema = "SHOP_SCHEMA")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
