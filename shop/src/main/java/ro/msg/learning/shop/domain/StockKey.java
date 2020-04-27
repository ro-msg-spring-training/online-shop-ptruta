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
public class StockKey implements Serializable {
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "location_id")
    private Integer locationId;
}