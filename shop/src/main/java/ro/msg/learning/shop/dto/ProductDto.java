package ro.msg.learning.shop.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ProductDto implements Serializable {

    private Integer id;

    private String productName;

    private String productDescription;

    private BigDecimal productPrice;

    private Double productWeight;

    private Integer productCategoryId;

    private Integer productSupplierId;

    private String productImageUrl;
}
