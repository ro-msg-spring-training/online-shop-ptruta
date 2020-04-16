package ro.msg.learning.shop.services.interfaces;

import ro.msg.learning.shop.dto.ProductDto;

import java.util.Collection;
import java.util.Optional;

public interface IProductService {
    ProductDto createProduct(ProductDto product);
    Optional<ProductDto> getProduct(Integer id);
    void updateProduct(Integer id, ProductDto product);
    void deleteProduct(Integer id);
    Collection<ProductDto> getAllProducts();
}
