package ro.msg.learning.shop.dto;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class ProductDto extends BaseDto{
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Double productWeight;
    private Integer productCategoryId;
    private Integer productSupplierId;
    private String productImageUrl;
}
