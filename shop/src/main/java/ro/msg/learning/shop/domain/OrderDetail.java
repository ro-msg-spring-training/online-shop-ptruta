package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderDetail {
    @Id
    @GeneratedValue
    private Integer order_detail_id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    private Integer quantity;
}
