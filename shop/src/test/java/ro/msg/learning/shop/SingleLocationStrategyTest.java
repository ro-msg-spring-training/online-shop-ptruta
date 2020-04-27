package ro.msg.learning.shop;

import org.junit.Test;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SingleLocationStrategyTest {
    @Mock
    private StockService stockService;
    @Mock
    private LocationService locationService;
    @InjectMocks
    private SingleLocationStrategy singleLocationStrategy;

    @Test
    public void TestGetProductLocation() throws LocationIdNotFoundException {
        // Given
        Map<Integer, Integer> listOfProducts = new HashMap<>();
        Supplier supplier = Supplier.builder().name("MarcoDpSRL").build();
        ProductCategory productCategory = ProductCategory.builder().name("Car").build();

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

        listOfProducts.put(firstProduct.getId(), 20);
        listOfProducts.put(secondProduct.getId(), 49);

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

        Stock firstStock = Stock.builder()
                .location(firstLocation)
                .product(firstProduct)
                .quantity(1300)
                .build();
        Stock secondStock = Stock.builder()
                .location(secondLocation)
                .product(secondProduct)
                .quantity(2000)
                .build();
        Stock thirdStock = Stock.builder()
                .location(firstLocation)
                .product(secondProduct)
                .quantity(1300)
                .build();
        Stock fourthStock = Stock.builder()
                .location(secondLocation)
                .product(firstProduct)
                .quantity(2000)
                .build();

        List<Location> locationList = new ArrayList<>();
        locationList.add(firstLocation);
        locationList.add(secondLocation);

        List<Stock> stocksListFirst = new ArrayList<>();
        stocksListFirst.add(firstStock);
        stocksListFirst.add(fourthStock);

        List<Stock> stocksListSecond = new ArrayList<>();
        stocksListSecond.add(secondStock);
        stocksListSecond.add(thirdStock);

        Mockito.when(locationService.getLocations())
                .thenReturn(locationList);

        Mockito.when(stockService.getStockByProductId(firstProduct.getId())).thenReturn(stocksListFirst);
        Mockito.when(stockService.getStockByProductId(secondProduct.getId())).thenReturn(stocksListSecond);

        // When
        List<Stock> result = singleLocationStrategy.getProductLocation(listOfProducts);

        // Then
        assertEquals(stocksListFirst.size(), result.size());
        assertThat(result).containsExactlyInAnyOrder(firstStock, thirdStock);
    }

}
