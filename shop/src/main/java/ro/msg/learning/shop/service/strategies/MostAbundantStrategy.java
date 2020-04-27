package ro.msg.learning.shop.service.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;
import ro.msg.learning.shop.service.implementation.LocationService;
import ro.msg.learning.shop.service.implementation.StockService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MostAbundantStrategy implements IWhichStrategy {
    private final StockService stockService;
    private final LocationService locationService;

    @Override
    @Transactional
    public List<Stock> getProductLocation(Map<Integer, Integer> stocks) throws LocationIdNotFoundException {
        List<Stock> finalStocks = new ArrayList<>();

        for (Map.Entry<Integer,Integer> stock : stocks.entrySet()) {
            Stock productStock = stockService
                    .getStockByProductId( stock.getKey())
                    .stream()
                    .filter(s -> s.getQuantity() >= stock.getValue()
                            && s.getProduct().getId().equals( stock.getKey()))
                    .max(Comparator.comparing(Stock::getQuantity)).orElse(null);
            if (productStock != null && productStock.getQuantity() > stock.getValue()) {
                finalStocks.add(productStock);
            }
        }

        if (finalStocks.size() > 0)
            return finalStocks;

        throw new LocationIdNotFoundException("Unable to find a suitable set of locations");
    }
}
