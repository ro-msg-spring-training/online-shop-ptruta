package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class StockDto extends BaseDto {
    private Product product;
    private Location location;
    private Integer quantity;
}
