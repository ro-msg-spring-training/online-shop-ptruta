package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductDto;

public class ProductConverter extends BaseConverter<Product, ProductDto> {
    @Override
    public Product convertDtoToModel(ProductDto dto) {
        Product product = Product.builder()
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
        ProductDto productDto = ProductDto.builder()
                                          .productName(product.getName())
                                          .productDescription(product.getDescription())
                                          .productImageUrl(product.getImageUrl())
                                          .productPrice(product.getPrice())
                                          .productWeight(product.getWeight())
                                          .build();
        productDto.setId(productDto.getId());
        return productDto;
    }
}
