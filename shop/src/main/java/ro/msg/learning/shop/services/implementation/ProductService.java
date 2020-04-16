package ro.msg.learning.shop.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.ProductConverter;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.domain.Supplier;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;
import ro.msg.learning.shop.services.interfaces.IProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;


    @Override
    public ProductDto createProduct(ProductDto product) {
        ProductConverter productConverter = new ProductConverter();
        Product product1 = productConverter.convertDtoToModel(product);
        List<ProductCategory> listOfCategories = productCategoryRepository.findAll();
        List<Supplier> listOfSuppliers = supplierRepository.findAll();
        ProductCategory productCategory = null;
        Supplier supplier = null;

        for (ProductCategory category : listOfCategories) {
            if (category.getId().equals(product.getProductCategoryId())) {
                productCategory = category;
            }
        }
        for (Supplier supplier1 : listOfSuppliers) {
            if (supplier1.getId().equals(product.getProductSupplierId())) {
                supplier = supplier1;
            }
        }

        product1.setProductCategory(productCategory);
        product1.setSupplier(supplier);
        productRepository.save(product1);
        return productConverter.convertModelToDto(product1);
    }

    @Override
    public Optional<ProductDto> getProduct(Integer id) {
        return Optional.empty();
    }

    @Override
    public void updateProduct(Integer id, ProductDto product) {

    }

    @Override
    public void deleteProduct(Integer id) {

    }

    @Override
    public Collection<ProductDto> getAllProducts() {
        return null;
    }
}
