package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;
import ro.msg.learning.shop.service.exceptions.ProductNoIdFoundException;
import ro.msg.learning.shop.service.exceptions.SupplierIdNotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;


    public Product createProduct(Product product) throws ProductNoIdFoundException, SupplierIdNotFoundException {
        productCategoryRepository.findById(product.getProductCategory().getId())
                .orElseThrow(() -> new ProductNoIdFoundException("No product id found" + product.getProductCategory().getId()));
        supplierRepository.findById(product.getSupplier().getId())
                .orElseThrow(() -> new SupplierIdNotFoundException("No supplier id found" + product.getSupplier().getId()));
        product.setId(product.getId());
        return productRepository.save(product);
    }


    public Product getProduct(final Integer id) throws ProductNoIdFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNoIdFoundException("No product with id" + id));
    }


    @Transactional
    public Product updateProduct(final Integer id, Product product) throws ProductNoIdFoundException {
        Product productFound = productRepository.findById(id)
                .orElseThrow(() -> new ProductNoIdFoundException("No product found with this id" + id));
        productFound.setProductCategory(product.getProductCategory());
        productFound.setSupplier(product.getSupplier());
        productFound.setId(id);
        productFound.setDescription(product.getDescription());
        productFound.setImageUrl(product.getImageUrl());
        productFound.setName(product.getName());
        productFound.setPrice(product.getPrice());
        productFound.setWeight(product.getWeight());
        return productRepository.save(productFound);
    }


    public Product deleteProduct(final Integer id) throws ProductNoIdFoundException {
        Product productFound = productRepository.findById(id)
                .orElseThrow(() -> new ProductNoIdFoundException("No product found with this id" + id));
        productRepository.deleteById(id);
        return productFound;
    }


    public List<Product> getAllProducts() {
        return new ArrayList<>(productRepository.findAll());
    }
}
