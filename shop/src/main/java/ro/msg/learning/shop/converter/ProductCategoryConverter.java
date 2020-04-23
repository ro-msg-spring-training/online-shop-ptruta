package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductCategoryDto;

@Component
public class ProductCategoryConverter extends BaseConverter<ProductCategory, ProductCategoryDto> {

    @Override
    public ProductCategory convertDtoToModel(ProductCategoryDto dto) {
        return ProductCategory.builder()
                .description(dto.getProductDescription())
                .name(dto.getProductCategoryName())
                .build();
    }

    @Override
    public ProductCategoryDto convertModelToDto(ProductCategory productCategory) {
        return ProductCategoryDto.builder()
                .id(productCategory.getId())
                .productDescription(productCategory.getDescription())
                .productCategoryName(productCategory.getName())
                .build();
    }
}
