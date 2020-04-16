package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class OrderDetailKey implements Serializable {
    @Column(name="order_id")
    private Integer orderId;
    @Column(name="product_id")
    private Integer productId;
}
