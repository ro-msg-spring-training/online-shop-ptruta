package ro.msg.learning.shop.service.implementation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class OrderServiceTest {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private StockService stockService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    @Value("${choose_strategy}")
    private String chosen_strategy;

    private void createElems(Product firstProduct, Product secondProduct,
                             Map<Integer, Integer> listOfProducts, Stock firstStock, Stock secondStock,
                             Stock thirdStock, Stock fourthStock,
                             Integer quantityInput1, Integer quantityInput2,
                             Integer quantityDb1, Integer quantityDb2, Integer quantityDb3, Integer quantityDb4,
                             Order order, Customer customer) {
        Supplier supplier = Supplier.builder().name("MarcoDpSRL").build();
        supplier = supplierService.createSupplier(supplier);
        assertEquals(supplierService.getSupplier(supplier.getId()), supplier);
        ProductCategory productCategory = ProductCategory.builder().name("Car").build();
        productCategory = productCategoryService.createProductCategory(productCategory);
        assertEquals(productCategoryService.getProductCategory(productCategory.getId()), productCategory);


        firstProduct.setId(1);
        firstProduct.setProductCategory(productCategory);
        firstProduct.setSupplier(supplier);
        firstProduct.setName("BMW S5");
        firstProduct.setDescription("Color: Brown, Size: Medium");
        firstProduct.setPrice(BigDecimal.valueOf(34554365));
        firstProduct.setWeight(23.99);
        firstProduct.setImageUrl("BMW");

        secondProduct.setId(2);
        secondProduct.setProductCategory(productCategory);
        secondProduct.setSupplier(supplier);
        secondProduct.setName("Volkswagen S5");
        secondProduct.setDescription("Color: Red, Size: Big");
        secondProduct.setPrice(BigDecimal.valueOf(34554365));
        secondProduct.setWeight(343.54);
        secondProduct.setImageUrl("BMW");

        firstProduct = productService.createProduct(firstProduct);
        secondProduct = productService.createProduct(secondProduct);

        listOfProducts.put(firstProduct.getId(), quantityInput1);
        listOfProducts.put(secondProduct.getId(), quantityInput2);

        Location firstLocation = Location.builder()
                .id(1)
                .name("Marco dp - Zalau")
                .addressCity("Zalau")
                .addressCountry("Romania")
                .addressCounty("Salaj")
                .addressStreetAddress("Bd-ul Mihai Viteazu nr104B")
                .build();
        Location secondLocation = Location.builder()
                .id(2)
                .name("Marco dp - Cluj")
                .addressCity("Cluj-Napoca")
                .addressCountry("Romania")
                .addressCounty("Cluj")
                .addressStreetAddress("Bd-ul Mihai Viteazu nr104B")
                .build();

        firstLocation = locationService.createLocation(firstLocation);
        secondLocation = locationService.createLocation(secondLocation);

        StockKey stockKey1 = StockKey.builder().productId(firstProduct.getId()).locationId(firstLocation.getId()).build();
        StockKey stockKey2 = StockKey.builder().productId(firstProduct.getId()).locationId(secondLocation.getId()).build();
        StockKey stockKey3 = StockKey.builder().productId(secondProduct.getId()).locationId(firstLocation.getId()).build();
        StockKey stockKey4 = StockKey.builder().productId(secondProduct.getId()).locationId(secondLocation.getId()).build();

        firstStock.setLocation(firstLocation);
        firstStock.setProduct(firstProduct);
        firstStock.setQuantity(quantityDb1);
        firstStock.setId(stockKey1);

        secondStock.setLocation(secondLocation);
        secondStock.setProduct(secondProduct);
        secondStock.setQuantity(quantityDb2);
        secondStock.setId(stockKey2);

        thirdStock.setLocation(firstLocation);
        thirdStock.setProduct(secondProduct);
        thirdStock.setQuantity(quantityDb3);
        thirdStock.setId(stockKey3);

        fourthStock.setLocation(secondLocation);
        fourthStock.setProduct(firstProduct);
        fourthStock.setQuantity(quantityDb4);
        fourthStock.setId(stockKey4);

        firstStock = stockService.createStock(firstStock);
        secondStock = stockService.createStock(secondStock);
        thirdStock = stockService.createStock(thirdStock);
        fourthStock = stockService.createStock(fourthStock);

        customer.setId(1);
        customer.setEmailAddress("truta_georgiana@yahoo.com");
        customer.setUserName("ptruta");
        customer.setLastName("Truta");
        customer.setFirstName("Patricia");

        customer = customerService.createCustomer(customer);

        order.setLocalDateTime(LocalDateTime.parse("2017-03-08T12:30:54"));
        order.setId(1);
        order.setShippedForm(firstLocation);
        order.setCustomer(customerService.getCustomer(customer.getId()));
        order.setAddressCity("Zalau");
        order.setAddressCounty("Salaj");
        order.setAddressCountry("Romania");
        order.setAddressStreetAddress("Bd-ul Mihai Viteazu");
    }

    @Test
    public void createOrderSuccessfully() {
        Product firstProduct = new Product();
        Product secondProduct = new Product();
        Map<Integer, Integer> listOfProducts = new HashMap<>();
        Stock firstStock = new Stock();
        Stock secondStock = new Stock();
        Stock thirdStock = new Stock();
        Stock fourthStock = new Stock();
        Customer customer = new Customer();
        Order order = new Order();
        List<Order> result;
        createElems(firstProduct, secondProduct, listOfProducts, firstStock,
                secondStock, thirdStock, fourthStock, 20, 49,
                1300, 2000, 20000, 4000, order, customer);
        result = orderService.createOrder(listOfProducts, order);
        if (chosen_strategy.equals("Single")) {
            assertEquals(result, Collections.singletonList(order));
        }

        if (chosen_strategy.equals("MostAbundant")) {
            assertThat(result).containsExactlyInAnyOrder(result.get(0), result.get(1));
            assertEquals(2, result.size());
        }
    }

    @Test(expected = LocationIdNotFoundException.class)
    public void createOrderFail() {
        Product firstProduct = new Product();
        Product secondProduct = new Product();
        Map<Integer, Integer> listOfProducts = new HashMap<>();
        Stock firstStock = new Stock();
        Stock secondStock = new Stock();
        Stock thirdStock = new Stock();
        Stock fourthStock = new Stock();
        Customer customer = new Customer();
        Order order = new Order();
        List<Order> result = new ArrayList<>();
        createElems(firstProduct, secondProduct, listOfProducts, firstStock,
                secondStock, thirdStock, fourthStock, 200000, 400000, 200, 400,
                500, 600, order, customer);
        result = orderService.createOrder(listOfProducts, order);

        assertNull(result);
        fail();
    }
}