package ro.msg.learning.shop.service.strategies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;
import ro.msg.learning.shop.service.implementation.LocationService;
import ro.msg.learning.shop.service.implementation.StockService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SingleLocationStrategyTest {
    private final Integer notGoodQuantity = 30000;
    @Mock
    private StockService stockService;
    @Mock
    private LocationService locationService;
    @InjectMocks
    private SingleLocationStrategy getStrategy;
    @Value("${choose_strategy}")
    private String chosen_strategy;

    private void createElems(List<Location> locationList,Product firstProduct, Product secondProduct,
                             Map<Integer, Integer> listOfProducts, List<Stock> stocksListFirst,
                             List<Stock> stocksListSecond, Stock firstStock, Stock secondStock,
                             Stock thirdStock, Stock fourthStock,
                             Integer quantityInput1, Integer quantityInput2,
                             Integer quantityDb1, Integer quantityDb2, Integer quantityDb3, Integer quantityDb4) {
        Supplier supplier = Supplier.builder().name("MarcoDpSRL").build();
        ProductCategory productCategory = ProductCategory.builder().name("Car").build();

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

        locationList.add(firstLocation);
        locationList.add(secondLocation);

        firstStock.setLocation(firstLocation);
        firstStock.setProduct(firstProduct);
        firstStock.setQuantity(quantityDb1);

        secondStock.setLocation(secondLocation);
        secondStock.setProduct(secondProduct);
        secondStock.setQuantity(quantityDb2);

        thirdStock.setLocation(firstLocation);
        thirdStock.setProduct(secondProduct);
        thirdStock.setQuantity(quantityDb3);

        fourthStock.setLocation(secondLocation);
        fourthStock.setProduct(firstProduct);
        fourthStock.setQuantity(quantityDb4);

        stocksListFirst.add(firstStock);
        stocksListFirst.add(fourthStock);

        stocksListSecond.add(secondStock);
        stocksListSecond.add(thirdStock);
    }

    @Test
    public void TestGetProductLocation() throws LocationIdNotFoundException {
        // Given
        List<Location> locationList = new ArrayList<>();
        Product firstProduct = new Product();
        Product secondProduct = new Product();
        Map<Integer, Integer> listOfProducts = new HashMap<>();
        List<Stock> stocksListFirst = new ArrayList<>();
        List<Stock> stocksListSecond = new ArrayList<>();
        Stock firstStock = new Stock();
        Stock secondStock = new Stock();
        Stock thirdStock = new Stock();
        Stock fourthStock = new Stock();
        createElems(locationList, firstProduct, secondProduct, listOfProducts, stocksListFirst, stocksListSecond, firstStock,
                secondStock, thirdStock, fourthStock, 20, 49,
                1300, 2000, 20000, 4000);

        Mockito.when(locationService.getLocations())
                .thenReturn(locationList);

        //mock for the products in list of stocks
        Mockito.when(stockService.getStockByProductId(firstProduct.getId())).thenReturn(stocksListFirst);
        Mockito.when(stockService.getStockByProductId(secondProduct.getId())).thenReturn(stocksListSecond);

        // When
        List<Stock> result = getStrategy.getProductLocation(listOfProducts);
        // Then
        assertEquals(stocksListFirst.size(), result.size());
        assertThat(result).containsExactlyInAnyOrder(firstStock, thirdStock);

    }

    @Test(expected = LocationIdNotFoundException.class)
    public void TestGetProductLocationException() {
        //Given
        List<Location> locationList = new ArrayList<>();
        Product firstProduct = new Product();
        Product secondProduct = new Product();
        Map<Integer, Integer> listOfProducts = new HashMap<>();
        List<Stock> stocksListFirst = new ArrayList<>();
        List<Stock> stocksListSecond = new ArrayList<>();
        Stock firstStock = new Stock();
        Stock secondStock = new Stock();
        Stock thirdStock = new Stock();
        Stock fourthStock = new Stock();
        createElems(locationList, firstProduct, secondProduct, listOfProducts, stocksListFirst, stocksListSecond, firstStock,
                secondStock, thirdStock, fourthStock,  200000, 400000, 200, 400,
                500, 600);

        Mockito.when(locationService.getLocations())
                .thenReturn(locationList);

        //mock for the products in list of stocks
        Mockito.when(stockService.getStockByProductId(firstProduct.getId())).thenReturn(stocksListFirst);
        Mockito.when(stockService.getStockByProductId(secondProduct.getId())).thenReturn(stocksListSecond);


        //mock for the product with quantity bigger not in list of stocks
        Mockito.when(stockService
                .getStockByProductId(firstProduct.getId())
                .stream()
                .filter(s -> s.getQuantity() >= notGoodQuantity)
                .collect(Collectors.toList()))
                .thenReturn(new ArrayList<>());


        // When
        List<Stock> result = getStrategy.getProductLocation(listOfProducts);
        // Then
        assertEquals(stocksListFirst.size(), result.size());
        assertThat(result).containsExactlyInAnyOrder(firstStock, thirdStock);


    }
}
