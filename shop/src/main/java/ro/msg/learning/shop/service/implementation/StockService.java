package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.StockConverter;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;
import ro.msg.learning.shop.service.exceptions.ProductNoIdFoundException;
import ro.msg.learning.shop.service.exceptions.StockLocationProductIdNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockConverter stockConverter;
    private final LocationRepository locationRepository;
    private final ProductRepository productRepository;


    public List<StockDto> getAllStocks(Integer locationId) {
        return stockRepository.findAll()
                .stream()
                .filter(stock -> stock.getLocation().getId().equals(locationId))
                .map(stockConverter::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Stock updateStock(Stock stock, Integer quantity){
        final Stock stockGot = stockRepository.findAll()
                .stream()
                .filter(s -> s.getLocation().getId().equals(stock.getLocation().getId())
                        && s.getProduct().getId().equals(stock.getProduct().getId()))
                .findFirst()
                .orElseThrow(() -> new StockLocationProductIdNotFoundException("No Stock with location and product id"));

        if (stockGot.getQuantity() >= stock.getQuantity()) {
            stockGot.setQuantity(stockGot.getQuantity() - quantity);
            return stockRepository.save(stockGot);
        } else {
            stockRepository.findAll().remove(stockGot);
            return null;
        }
    }

    public List<Stock> getSockByProduct(final Product productId) {
        return stockRepository.findStockByProduct(productId);
    }

    public List<Stock> getStockByProductId(final Integer productId) {
        return stockRepository.findStockByProductId(productId);
    }

    public List<Stock> getStockByLocation(final Location locationId) {
        return stockRepository.findStockByLocation(locationId);
    }

    public Stock createStock(Stock stock) {
        productRepository.findById(stock.getProduct().getId())
                .orElseThrow(() -> new ProductNoIdFoundException("No product id found" + stock.getProduct().getId()));
        locationRepository.findById(stock.getLocation().getId())
                .orElseThrow(() -> new LocationIdNotFoundException("No location id found" + stock.getLocation().getId()));

        return stockRepository.save(stock);
    }

    public List<Stock> saveStocks(List<Stock> stocks){
        return stockRepository.saveAll(stocks);
    }
}

