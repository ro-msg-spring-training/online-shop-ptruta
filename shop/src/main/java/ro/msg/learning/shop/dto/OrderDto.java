package ro.msg.learning.shop.dto;

import jdk.internal.net.http.common.Pair;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class OrderDto implements Serializable {
    private Integer id;
    private Integer customerId;
    private LocalDateTime localDateTime;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
    private List<OrderDetailDto> orderDetails = new ArrayList<>();
}
