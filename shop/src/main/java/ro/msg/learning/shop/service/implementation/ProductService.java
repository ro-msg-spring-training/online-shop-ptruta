package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.ProductConverter;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;
import ro.msg.learning.shop.service.interfaces.IProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductConverter productConverter;


    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productConverter.convertDtoToModel(productDto);
        product.setProductCategory(productCategoryRepository.findById(productDto.getProductCategoryId())
                .orElseThrow(() -> new RuntimeException("No category id set on dto!")));
        product.setSupplier(supplierRepository.findById(productDto.getProductSupplierId())
                .orElseThrow(() -> new RuntimeException("No supplier id set on dto!")));
        Product savedProduct = productRepository.save(product);
        return productConverter.convertModelToDto(savedProduct);
    }

    @Override
    public ProductDto getProduct(final Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No product with id" + id));
        return productConverter.convertModelToDto(product);
    }

    @Override
    public ProductDto updateProduct(final Integer id, ProductDto productDto) {
        Product product = productConverter.convertDtoToModel(productDto);
        Product productFound = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No product found"));
        productFound.setProductCategory(product.getProductCategory());
        productFound.setSupplier(product.getSupplier());
        productFound.setId(id);
        productFound.setDescription(product.getDescription());
        productFound.setImageUrl(product.getImageUrl());
        productFound.setName(product.getName());
        productFound.setPrice(product.getPrice());
        productFound.setWeight(product.getWeight());
        Product savedProduct = productRepository.save(productFound);
        return productConverter.convertModelToDto(savedProduct);
    }

    @Override
    public void deleteProduct(final Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productConverter::convertModelToDto)
                .collect(Collectors.toList());
    }
}
