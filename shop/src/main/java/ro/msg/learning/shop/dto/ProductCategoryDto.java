package ro.msg.learning.shop.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class ProductCategoryDto extends BaseDto {
    private String productCategoryName;
    private String productDescription;
}
