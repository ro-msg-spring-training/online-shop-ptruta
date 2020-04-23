package ro.msg.learning.shop.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ProductCategoryDto implements Serializable {
    private Integer id;
    private String productCategoryName;
    private String productDescription;
}
