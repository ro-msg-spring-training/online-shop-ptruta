package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Customer;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class OrderDto extends BaseDto {
        private Customer customerId;
        private LocalDateTime localDateTime;
        private String country;
        private String city;
        private String county;
        private String streetAddress;
        private List<OrderDetailDto> orderDetailDtos;
}
