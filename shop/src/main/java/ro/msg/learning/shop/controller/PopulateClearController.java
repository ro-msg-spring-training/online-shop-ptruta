package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.implementation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Profile("test")
@RequestMapping("/test")
@RequiredArgsConstructor
public class PopulateClearController {
    private final SupplierService supplierService;
    private final StockService stockService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final LocationService locationService;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final SupplierRepository supplierRepository;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final LocationRepository locationRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @PostMapping(value = "/populate", produces = "application/json")
    @Transactional
    public ResponseEntity<String> populateDatabase() {
        Map<Integer, Integer> listOfProducts = new HashMap<>();
        Supplier supplier = Supplier.builder().id(1).name("MarcoDpSRL").build();
        supplier = supplierService.createSupplier(supplier);

        ProductCategory productCategory = ProductCategory.builder().id(1).name("Car").build();
        productCategory = productCategoryService.createProductCategory(productCategory);

        Product firstProduct = Product.builder()
                .id(1)
                .productCategory(productCategory)
                .supplier(supplier)
                .name("BMW S5")
                .price(BigDecimal.valueOf(34554365))
                .description("Color: Brown, Size: Medium")
                .weight(23.99)
                .imageUrl("BMW")
                .build();
        Product secondProduct = Product.builder()
                .id(2)
                .productCategory(productCategory)
                .supplier(supplier)
                .name("Volkswagen S5")
                .price(BigDecimal.valueOf(34554365))
                .description("Color: Red, Size: Big")
                .weight(343.54)
                .build();

        firstProduct = productService.createProduct(firstProduct);
        secondProduct = productService.createProduct(secondProduct);

        listOfProducts.put(firstProduct.getId(), 20);
        listOfProducts.put(secondProduct.getId(), 49);

        String city = "Zalau";
        String country = "Romania";
        String county = "Salaj";
        Location firstLocation = Location.builder()
                .id(1)
                .name("Marco dp - Zalau")
                .addressCity("Oradea")
                .addressCountry("Romania")
                .addressCounty("Bihor")
                .addressStreetAddress("Bd-ul Mihai Viteazu nr104B")
                .build();
        Location secondLocation = Location.builder()
                .id(2)
                .name("Marco dp - Cluj")
                .addressCity("Cluj-Napoca")
                .addressCountry(country)
                .addressCounty("Cluj")
                .addressStreetAddress("Bd-ul Mihai Viteazu nr104B")
                .build();
        firstLocation = locationService.createLocation(firstLocation);
        secondLocation = locationService.createLocation(secondLocation);

        StockKey stockKeyFirst = StockKey.builder()
                .locationId(firstLocation.getId())
                .productId(firstProduct.getId())
                .build();
        StockKey stockKeySecond = StockKey.builder()
                .locationId(firstLocation.getId())
                .productId(secondProduct.getId())
                .build();
        StockKey stockKeyThird = StockKey.builder()
                .locationId(secondLocation.getId())
                .productId(firstProduct.getId())
                .build();
        StockKey stockKeyFourth = StockKey.builder()
                .locationId(secondLocation.getId())
                .productId(secondProduct.getId())
                .build();

        Stock firstStock = Stock.builder()
                .id(stockKeyFirst)
                .location(firstLocation)
                .product(firstProduct)
                .quantity(1300)
                .build();
        Stock secondStock = Stock.builder()
                .id(stockKeySecond)
                .location(firstLocation)
                .product(secondProduct)
                .quantity(2000)
                .build();
        Stock thirdStock = Stock.builder()
                .id(stockKeyThird)
                .location(secondLocation)
                .product(firstProduct)
                .quantity(20000)
                .build();
        Stock fourthStock = Stock.builder()
                .id(stockKeyFourth)
                .location(secondLocation)
                .product(secondProduct)
                .quantity(4000)
                .build();

        stockService.createStock(firstStock);
        stockService.createStock(secondStock);
        stockService.createStock(thirdStock);
        stockService.createStock(fourthStock);

        Customer customer = Customer.builder()
                .id(1)
                .userName("ptruta")
                .firstName("Patricia")
                .lastName("Truta")
                .emailAddress("truta_georgiana@yahoo.com")
                .build();

        customer = customerService.createCustomer(customer);

        Order order = Order.builder()
                .id(1)
                .shippedForm(firstLocation)
                .customer(customer)
                .addressStreetAddress("Bd-ul Mihai Viteazu")
                .addressCounty(county)
                .addressCountry(country)
                .addressCity(city)
                .localDateTime(LocalDateTime.parse("2017-03-08T12:30:54"))
                .build();
        orderService.createOrder(listOfProducts, order);

        return ResponseEntity.accepted().body("Database populated successfully");
    }

    @DeleteMapping(value = "/clear", produces = "application/json")
    @Transactional
    public ResponseEntity<String> clearDatabase() {
        orderDetailRepository.deleteAll();
        orderRepository.deleteAll();
        stockRepository.deleteAll();
        locationRepository.deleteAll();
        productRepository.deleteAll();
        supplierRepository.deleteAll();
        productCategoryRepository.deleteAll();
        customerRepository.deleteAll();
        return ResponseEntity.accepted().body("Database cleared correctly!");
    }
}
