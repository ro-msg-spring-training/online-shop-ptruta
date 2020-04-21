package ro.msg.learning.shop.service.interfaces;
import ro.msg.learning.shop.dto.ProductCategoryDto;

import java.util.Optional;

public interface IProductCategoryService {
    Optional<ProductCategoryDto> getProductCategory(Integer id);
}
