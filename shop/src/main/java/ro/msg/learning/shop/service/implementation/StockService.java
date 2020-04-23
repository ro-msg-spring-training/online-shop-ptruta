package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.StockConverter;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.exceptions.StockLocationProductIdNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockConverter stockConverter;


    public List<StockDto> getAllStocks(Integer locationId) {
        return stockRepository.findAll()
                .stream()
                .filter(stock -> stock.getLocation().getId().equals(locationId))
                .map(stockConverter::convertModelToDto)
                .collect(Collectors.toList());
    }


    public StockDto updateStock(Integer locationId, Integer productId, Integer quantity) throws StockLocationProductIdNotFoundException {
        final Stock stockGot = stockRepository.findAll()
                .stream()
                .filter(stock -> stock.getLocation().getId().equals(locationId) && stock.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new StockLocationProductIdNotFoundException("No Stock with location and product id"));
        stockGot.setQuantity(stockGot.getQuantity() - quantity);
        return stockConverter.convertModelToDto(stockGot);
    }
}

