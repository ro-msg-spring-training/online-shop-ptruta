package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.Product;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class OrderDetailDto implements Serializable {

    private LocationDto location;

    private ProductDto product;

    private Integer quantity;
}
