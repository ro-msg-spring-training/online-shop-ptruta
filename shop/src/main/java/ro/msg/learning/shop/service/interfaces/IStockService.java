package ro.msg.learning.shop.service.interfaces;

import ro.msg.learning.shop.dto.StockDto;

import java.util.List;

public interface IStockService {
    List<StockDto> getAllStocks(Integer locationId);
    StockDto updateStock(Integer locationId, Integer productId, Integer quantity);
    StockDto getStockByProductIdAndLocationId(Integer productId, Integer locationId);
}
