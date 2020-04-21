package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;

@Component
public class StockConverter extends BaseConverter<Stock, StockDto> {
    @Override
    public Stock convertDtoToModel(StockDto dto) {
        return Stock.builder()
                .location(dto.getLocation())
                .product(dto.getProduct())
                .quantity(dto.getQuantity())
                .build();
    }

    @Override
    public StockDto convertModelToDto(Stock stock) {
        return StockDto.builder()
                .location(stock.getLocation())
                .product(stock.getProduct())
                .quantity(stock.getQuantity())
                .build();
    }
}
