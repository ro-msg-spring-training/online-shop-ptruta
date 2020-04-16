package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.Product;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class OrderDetailDto extends BaseDto {
    private Order order;
    private Product product;
    private Integer quantity;
}
