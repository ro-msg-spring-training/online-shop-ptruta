package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.service.implementation.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(path = "/products", produces = "application/json")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/products/{id}")
    public ProductDto getProduct(@PathVariable("id") final Integer id) {
        return productService.getProduct(id);
    }

    @PostMapping(value = "/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody final ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping(value = "/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateProduct(@PathVariable("id") final Integer id, @RequestBody final ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping(value = "/products/{id}")
    public void deleteProduct(@PathVariable("id") final Integer id) {
        productService.deleteProduct(id);
    }
}
