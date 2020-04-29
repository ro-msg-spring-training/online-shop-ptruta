package ro.msg.learning.shop.service.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;
import ro.msg.learning.shop.service.implementation.StockService;
import ro.msg.learning.shop.service.strategies.configuration.IWhichStrategy;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MostAbundantStrategy implements IWhichStrategy {
    private final StockService stockService;

    @Override
    @Transactional
    public List<Stock> getProductLocation(Map<Integer, Integer> productQuantityListInput) {
        List<Stock> finalStocks = new ArrayList<>();

        for (Map.Entry<Integer, Integer> productQuantity : productQuantityListInput.entrySet()) {
            Stock productStock = stockService
                    .getStockByProductId(productQuantity.getKey())
                    .stream()
                    .filter(s -> s.getQuantity() >= productQuantity.getValue()
                            && s.getProduct().getId().equals(productQuantity.getKey()))
                    .max(Comparator.comparing(Stock::getQuantity)).orElse(null);
            if (productStock != null && productStock.getQuantity() > productQuantity.getValue()) {
                finalStocks.add(productStock);
            }
        }

        if (finalStocks.size() > 0) {
            return finalStocks;
        }

        throw new LocationIdNotFoundException("Unable to find a suitable set of locations");
    }
}
