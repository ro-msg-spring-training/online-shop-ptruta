package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.ProductConverter;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.service.exceptions.ProductNoIdFoundException;
import ro.msg.learning.shop.service.exceptions.SupplierIdNotFoundException;
import ro.msg.learning.shop.service.implementation.ProductService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping(path = "/products", produces = "application/json")
    @Transactional
    public ResponseEntity<List<ProductDto>> getAllProducts() {

        List<Product> allProducts = productService.getAllProducts();

        List<ProductDto> allProductsDto = productConverter.convertModelsToDtos(allProducts);

        return getResponses(allProductsDto);
    }


    @GetMapping(value = "/products/{id}", produces = "application/json")
    @Transactional
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") final Integer id){

        Product product = productService.getProduct(id);

        ProductDto productDto = productConverter.convertModelToDto(product);

        return getResponse(productDto);
    }

    @PostMapping(value = "/products", produces = "application/json")
    @Transactional
    public ResponseEntity<ProductDto> createProduct(@RequestBody final ProductDto product)
            throws ProductNoIdFoundException, SupplierIdNotFoundException {

        Product productConverted = productConverter.convertDtoToModel(product);

        productService.createProduct(productConverted);

        ProductDto productDto = productConverter.convertModelToDto(productConverted);

        return getResponse(productDto);
    }

    @PutMapping(value = "/products/{id}", produces = "application/json")
    @Transactional
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") final Integer id,
                                                    @RequestBody final ProductDto productDto)
            throws ProductNoIdFoundException {

        Product productConverted = productConverter.convertDtoToModel(productDto);

        productService.updateProduct(id, productConverted);

        ProductDto productDt = productConverter.convertModelToDto(productConverted);

        return getResponse(productDt);

    }

    @DeleteMapping(value = "/products/{id}", produces = "application/json")
    @Transactional
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") final Integer id) throws ProductNoIdFoundException {

        Product productDeleted = productService.deleteProduct(id);

        ProductDto productDto = productConverter.convertModelToDto(productDeleted);

        return getResponse(productDto);
    }

    public ResponseEntity<ProductDto> getResponse(ProductDto productDto) {

        return ResponseEntity.accepted().body(productDto);
    }

    public ResponseEntity<List<ProductDto>> getResponses(List<ProductDto> productDtos) {

        return ResponseEntity.accepted().body(productDtos);
    }
}
