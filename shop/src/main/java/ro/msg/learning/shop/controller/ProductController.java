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

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final String headerName = "Responded";
    private final String headerValue = "ProductController";

    @GetMapping(path = "/products", produces = "application/json")
    public ResponseEntity<List<ProductDto>> getAllProducts() {

        List<Product> allProducts = productService.getAllProducts();

        List<ProductDto> allProductsDto = productConverter.convertModelsToDtos(allProducts);

        return getResponses(allProductsDto);
    }


    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") final Integer id)
            throws ProductNoIdFoundException {

        Product product = productService.getProduct(id);

        ProductDto productDto = productConverter.convertModelToDto(product);

        return getResponse(productDto);
    }

    @PostMapping(value = "/products", produces = "application/json")
    public ResponseEntity<ProductDto> createProduct(@RequestBody final ProductDto product)
            throws ProductNoIdFoundException, SupplierIdNotFoundException {

        Product productConverted = productConverter.convertDtoToModel(product);

        Product productCreated = productService.createProduct(productConverted);

        ProductDto productDto = productConverter.convertModelToDto(productCreated);

        return getResponse(productDto);
    }

    @PutMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") final Integer id,
                                                    @RequestBody final ProductDto productDto) throws ProductNoIdFoundException {

        Product productConverted = productConverter.convertDtoToModel(productDto);

        Product productUpdated = productService.updateProduct(id, productConverted);

        ProductDto productDt = productConverter.convertModelToDto(productUpdated);

        return getResponse(productDt);

    }

    @DeleteMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") final Integer id) throws ProductNoIdFoundException {

        Product productDeleted = productService.deleteProduct(id);

        ProductDto productDto = productConverter.convertModelToDto(productDeleted);

        return getResponse(productDto);
    }

    public ResponseEntity<ProductDto> getResponse(ProductDto productDto) {
        var headers = new HttpHeaders();
        headers.add(headerName, headerValue);

        return ResponseEntity.accepted().headers(headers).body(productDto);
    }

    public ResponseEntity<List<ProductDto>> getResponses(List<ProductDto> productDtos) {
        var headers = new HttpHeaders();
        headers.add(headerName, headerValue);

        return ResponseEntity.accepted().headers(headers).body(productDtos);
    }
}
