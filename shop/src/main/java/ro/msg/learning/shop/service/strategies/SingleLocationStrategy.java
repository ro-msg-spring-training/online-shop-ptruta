package ro.msg.learning.shop.service.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SingleLocationStrategy implements IWhichStrategy {
    private final StockRepository stockRepository;

    @Override
    public List<Stock> getProductLocation(final Integer productId, final Integer quantity) {
        List<Stock> stocks = stockRepository.findAll();
        List<Location> locations = new ArrayList<>();
        List<Location> sortedLocations;
        List<Stock> finalStocks = new ArrayList<>();

        for (Stock stock : stocks) {
            if (stock.getQuantity() >= quantity
                    && stock.getProduct().getId().equals(productId)) {
                locations.add(stock.getLocation());
            }
        }

        sortedLocations = locations.stream()
                .sorted(Comparator.comparing(Location::getId))
                .collect(Collectors.toList());

        for (Stock stock : stocks) {
            for (Location location : sortedLocations) {
                if (stock.getLocation().equals(location)) {
                    finalStocks.add(stock);
                }
            }
        }
        return finalStocks;
    }
}
