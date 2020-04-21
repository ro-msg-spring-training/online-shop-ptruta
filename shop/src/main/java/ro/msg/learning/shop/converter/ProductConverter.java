package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.domain.Supplier;
import ro.msg.learning.shop.dto.ProductDto;

import java.util.Optional;

@Component
public class ProductConverter extends BaseConverter<Product, ProductDto> {
    @Override
    public Product convertDtoToModel(ProductDto dto) {
        Product product = Product.builder()
                .id(dto.getId())
                .name(dto.getProductName())
                .description(dto.getProductDescription())
                .imageUrl(dto.getProductImageUrl())
                .price(dto.getProductPrice())
                .weight(dto.getProductWeight())
                .build();
        product.setId(dto.getId());
        return product;
    }

    @Override
    public ProductDto convertModelToDto(Product product) {
        Integer productCategoryId = Optional.ofNullable(product.getProductCategory())
                .map(ProductCategory::getId)
                .orElse(null);
        Integer productSupplierId = Optional.ofNullable(product.getSupplier())
                .map(Supplier::getId)
                .orElse(null);
        ProductDto productDto = ProductDto.builder()
                .productName(product.getName())
                .productDescription(product.getDescription())
                .productImageUrl(product.getImageUrl())
                .productCategoryId(productCategoryId)
                .productSupplierId(productSupplierId)
                .productPrice(product.getPrice())
                .productWeight(product.getWeight())
                .build();
        productDto.setId(product.getId());
        return productDto;
    }
}
