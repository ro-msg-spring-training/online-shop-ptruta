package ro.msg.learning.shop.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.domain.Supplier;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.service.implementation.ProductCategoryService;
import ro.msg.learning.shop.service.implementation.SupplierService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductConverter extends BaseConverter<Product, ProductDto> {
    private final ProductCategoryService productCategoryService;
    private final SupplierService supplierService;

    @Override
    public Product convertDtoToModel(ProductDto dto) {
        ProductCategory productCategory = productCategoryService.getProductCategory(dto.getProductCategoryId());
        Supplier supplier = supplierService.getSupplier(dto.getProductSupplierId());

        Product product = Product.builder()
                .id(dto.getId())
                .name(dto.getProductName())
                .description(dto.getProductDescription())
                .imageUrl(dto.getProductImageUrl())
                .price(dto.getProductPrice())
                .productCategory(productCategory)
                .supplier(supplier)
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
                .id(product.getId())
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

    @Override
    public List<Product> convertDtosToModels(Collection<ProductDto> productDtos) {
        return super.convertDtosToModels(productDtos);
    }

    @Override
    public List<ProductDto> convertModelsToDtos(Collection<Product> products) {
        return super.convertModelsToDtos(products);
    }
}
