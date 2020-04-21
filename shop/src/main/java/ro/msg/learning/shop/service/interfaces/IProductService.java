package ro.msg.learning.shop.service.interfaces;

import ro.msg.learning.shop.dto.ProductDto;

import java.util.List;

public interface IProductService {
    ProductDto createProduct(ProductDto product);

    ProductDto getProduct(Integer id);

    ProductDto updateProduct(Integer id, ProductDto product);

    void deleteProduct(Integer id);

    List<ProductDto> getAllProducts();
}
