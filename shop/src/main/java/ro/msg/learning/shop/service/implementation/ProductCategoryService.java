package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.ProductCategoryConverter;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.service.interfaces.IProductCategoryService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryService implements IProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public Optional<ProductCategoryDto> getProductCategory(final Integer id) {
        ProductCategory productCategory = productCategoryRepository.getOne(id);
        ProductCategoryConverter productCategoryConverter = new ProductCategoryConverter();
        return Optional.ofNullable(productCategoryConverter.convertModelToDto(productCategory));
    }
}
