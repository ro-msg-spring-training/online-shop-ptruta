package ro.msg.learning.shop.service.strategies;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.LocationConverter;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.dto.ProductQuantityDto;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MostAbundantStrategy implements IWhichStrategy{
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public List<Stock> getProductLocation(final Integer productId, final Integer quantity) {
        List<Location> locations = new ArrayList<>();
        List<Stock> stocks = stockRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<Stock> finalStocks = new ArrayList<>();

        for (Stock stock : stocks) {
                if (stock.getQuantity() >= quantity
                        && stock.getProduct().getId().equals(productId)) {
                    locations.add(stock.getLocation());
                }
            }

        for (Stock stock : stocks) {
            for (Product prod : products) {
                if (stock.getProduct().equals(prod)) {
                    Integer max = stock.getQuantity();
                    for (Location location : locations) {
                        if (stock.getLocation().equals(location)) {
                            if (stock.getQuantity() > max) {
                                max = stock.getQuantity();
                            }
                        }
                    }
                    if (stock.getQuantity().equals(max)){
                        finalStocks.add(stock);
                    }
                }
            }
        }
        return finalStocks;
    }
}
