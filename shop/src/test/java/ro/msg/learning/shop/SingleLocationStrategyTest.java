package ro.msg.learning.shop;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;
import ro.msg.learning.shop.service.implementation.LocationService;
import ro.msg.learning.shop.service.implementation.StockService;
import ro.msg.learning.shop.service.strategies.SingleLocationStrategy;

import java.math.BigDecimal;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class SingleLocationStrategyTest {
    @Mock
    private StockService stockService;
    @Mock
    private LocationService locationService;
    @InjectMocks
    private SingleLocationStrategy singleLocationStrategy;

    @Test
    public void getProductLocationTest() throws LocationIdNotFoundException {
        Map<Integer, Integer> listOfProducts = new HashMap<>();
        Supplier supplier = Supplier.builder().name("MarcoDpSRL").build();
        ProductCategory productCategory = ProductCategory.builder().name("Car").build();

        Product firstProduct = Product.builder().productCategory(productCategory).supplier(supplier)
                .name("BMW S5").price(BigDecimal.valueOf(34554365))
                .description("Color: Brown, Size: Medium")
                .weight(23.99)
                .imageUrl("BMW")
                .build();
        Product secondProduct = Product.builder().productCategory(productCategory).supplier(supplier)
                .name("Volkswagen S5").price(BigDecimal.valueOf(34554365))
                .description("Color: Red, Size: Big")
                .weight(343.54)
                .build();

        listOfProducts.put(firstProduct.getId(), 20);
        listOfProducts.put(secondProduct.getId(), 49);

        Location firstLocation = Location.builder()
                .name("Marco dp - Zalau")
                .addressCity("Zalau")
                .addressCountry("Romania")
                .addressCounty("Salaj")
                .addressStreetAddress("Bd-ul Mihai Viteazu nr104B")
                .build();
        Location secondLocation = Location.builder()
                .name("Marco dp - Cluj")
                .addressCity("Cluj-Napoca")
                .addressCountry("Romania")
                .addressCounty("Cluj")
                .addressStreetAddress("Bd-ul Mihai Viteazu nr104B")
                .build();

        Stock firstStock = Stock.builder()
                .location(firstLocation)
                .product(firstProduct)
                .quantity(1300).build();
        Stock secondStock = Stock.builder()
                .location(secondLocation)
                .product(secondProduct)
                .quantity(2000).build();
        Stock thirdStock = Stock.builder()
                .location(firstLocation)
                .product(secondProduct)
                .quantity(1300).build();
        Stock fourthStock = Stock.builder()
                .location(secondLocation)
                .product(firstProduct)
                .quantity(2000).build();

        List<Location> locationList = new ArrayList<>();
        locationList.add(firstLocation);
        locationList.add(secondLocation);

        List<Stock> stocksListFirst = new ArrayList<>();
        stocksListFirst.add(firstStock);
        stocksListFirst.add(thirdStock);

        List<Stock> stocksListSecond = new ArrayList<>();
        stocksListFirst.add(secondStock);
        stocksListFirst.add(fourthStock);

        Mockito.when(locationService.getLocations()).thenReturn(locationList);

        Mockito.when(stockService.getStockByProductId(firstProduct.getId())).thenReturn(stocksListFirst);
        Mockito.when(stockService.getStockByProductId(secondProduct.getId())).thenReturn(stocksListSecond);

        assert (singleLocationStrategy.getProductLocation(listOfProducts).equals(stocksListFirst));
    }

}
