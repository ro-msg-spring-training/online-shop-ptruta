package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Location;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class RevenueDto extends BaseDto{
    private Location revenueLocation;
    private LocalDate localDate;
    private BigDecimal sumOfRevenue;
}
