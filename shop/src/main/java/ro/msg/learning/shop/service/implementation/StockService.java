package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.StockConverter;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.interfaces.IStockService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {

    private final StockRepository stockRepository;
    private final StockConverter stockConverter;

    @Override
    public List<StockDto> getAllStocks(Integer locationId) {
        return stockRepository.findAll()
                .stream()
                .filter(stock -> stock.getLocation().getId().equals(locationId))
                .map(stockConverter::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StockDto updateStock(Integer locationId, Integer productId, Integer quantity) {
        final Stock stockGot = stockRepository.findAll()
                .stream()
                .filter(stock -> stock.getLocation().getId().equals(locationId) && stock.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(()->new RuntimeException("No Stock with location and product id"));
        stockGot.setQuantity(quantity);
        return stockConverter.convertModelToDto(stockGot);
    }

    @Override
    public StockDto getStockByProductIdAndLocationId(Integer productId, Integer locationId) {
        return stockRepository.findAll()
                .stream()
                .filter(stock -> stock.getLocation().getId().equals(locationId) && stock.getProduct().getId().equals(productId))
                .map(stockConverter::convertModelToDto)
                .findFirst()
                .orElseThrow(()->new RuntimeException("No Stock with location and product id"));
    }
}

