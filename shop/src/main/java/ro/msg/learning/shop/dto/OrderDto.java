package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.Location;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class OrderDto extends BaseDto {
        private Location shippedFormId;
        private Customer customerId;
        private LocalDateTime localDateTime;
        private String country;
        private String city;
        private String county;
        private String streetAddress;
}
