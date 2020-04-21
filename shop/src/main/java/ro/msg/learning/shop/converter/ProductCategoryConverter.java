package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductCategoryDto;

@Component
public class ProductCategoryConverter extends BaseConverter<ProductCategory, ProductCategoryDto> {

    @Override
    public ProductCategory convertDtoToModel(ProductCategoryDto dto) {
        ProductCategory productCategory = ProductCategory.builder()
                .description(dto.getProductDescription())
                .name(dto.getProductCategoryName())
                .build();
        productCategory.setId(dto.getId());
        return productCategory;
    }

    @Override
    public ProductCategoryDto convertModelToDto(ProductCategory productCategory) {
        ProductCategoryDto productCategoryDto = ProductCategoryDto.builder()
                .productDescription(productCategory.getDescription())
                .productCategoryName(productCategory.getName())
                .build();
        productCategoryDto.setId(productCategory.getId());
        return productCategoryDto;
    }
}
