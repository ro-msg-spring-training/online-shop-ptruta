package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class StockDto implements Serializable {

    private Integer productId;

    private Integer locationId;

    private Integer quantity;
}
