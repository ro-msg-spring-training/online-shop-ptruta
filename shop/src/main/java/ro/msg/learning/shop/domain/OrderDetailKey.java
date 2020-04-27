package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class OrderDetailKey implements Serializable {
    @Column(name = "orderr_id")
    private Integer orderId;

    @Column(name = "product_id")
    private Integer productId;
}
