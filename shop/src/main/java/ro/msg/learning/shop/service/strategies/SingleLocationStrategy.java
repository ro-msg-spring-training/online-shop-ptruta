package ro.msg.learning.shop.service.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;
import ro.msg.learning.shop.service.implementation.LocationService;
import ro.msg.learning.shop.service.implementation.StockService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SingleLocationStrategy implements IWhichStrategy {
    private final StockService stockService;
    private final LocationService locationService;

    @Override
    @Transactional
    public List<Stock> getProductLocation(Map<Integer, Integer> productQuantityListInput){
        List<Location> locations = locationService.getLocations();

        for (Location location : locations) {
            List<Stock> productsStocks = new ArrayList<>();
            for (Map.Entry<Integer, Integer> productQuantity : productQuantityListInput.entrySet()) {
                Stock newStock = stockService
                        .getStockByProductId(productQuantity.getKey())
                        .stream()
                        .filter(s -> s.getQuantity() >= productQuantity.getValue()
                                && s.getProduct().getId().equals(productQuantity.getKey())
                                && s.getLocation().equals(location))
                        .findFirst()
                        .orElse(null);

                if (newStock != null && newStock.getQuantity() > productQuantity.getValue() &&
                        !productsStocks.contains(newStock)) {
                    productsStocks.add(newStock);
                }
            }
            if (productsStocks.size() == productQuantityListInput.size())
                return productsStocks;
        }

        throw new LocationIdNotFoundException("Unable to find a suitable set of locations");
    }
}
