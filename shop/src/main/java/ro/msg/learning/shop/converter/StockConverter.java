package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;

import java.util.Optional;

@Component
public class StockConverter extends BaseConverter<Stock, StockDto> {
    @Override
    public Stock convertDtoToModel(StockDto dto) {
        return Stock.builder()
                .quantity(dto.getQuantity())
                .build();
    }

    @Override
    public StockDto convertModelToDto(Stock stock) {
        Integer locationId = Optional.ofNullable(stock.getLocation())
                .map(Location::getId)
                .orElse(null);
        Integer productId = Optional.ofNullable(stock.getProduct())
                .map(Product::getId)
                .orElse(null);
        return StockDto.builder()
                .locationId(locationId)
                .productId(productId)
                .quantity(stock.getQuantity())
                .build();
    }
}
