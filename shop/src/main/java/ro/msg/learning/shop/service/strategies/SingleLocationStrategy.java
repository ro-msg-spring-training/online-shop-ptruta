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
    public List<Stock> getProductLocation(Map<Integer, Integer> stocks) throws LocationIdNotFoundException {
        List<Location> locations = locationService.getLocations();

        for (Location location : locations) {
            List<Stock> productsStocks = new ArrayList<>();
            for (Map.Entry<Integer, Integer> stock : stocks.entrySet()) {
                Stock newStock = stockService
                        .getStockByProductId(stock.getKey())
                        .stream()
                        .filter(s -> s.getQuantity() >= stock.getValue()
                                && s.getProduct().getId().equals(stock.getKey())
                                && s.getLocation().equals(location))
                        .findFirst()
                        .orElse(null);

                if (newStock != null && newStock.getQuantity() > (Integer) stock.getValue() &&
                        !productsStocks.contains(newStock)) {
                    productsStocks.add(newStock);
                }
            }
            if (productsStocks.size() == stocks.size())
                return productsStocks;
        }

        throw new LocationIdNotFoundException("Unable to find a suitable set of locations");
    }
}
