package ro.msg.learning.shop.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class SupplierDto extends BaseDto{
    private String supplierName;
}
