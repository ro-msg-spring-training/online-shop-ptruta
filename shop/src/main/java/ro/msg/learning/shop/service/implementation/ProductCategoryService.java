package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;


    public ProductCategory getProductCategory(final Integer id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    public ProductCategory createProductCategory(final ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }
}
