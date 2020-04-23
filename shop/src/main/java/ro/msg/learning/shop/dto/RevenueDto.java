package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Location;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class RevenueDto implements Serializable {
    private Integer id;
    private LocalDate localDate;
    private BigDecimal sumOfRevenue;
}
