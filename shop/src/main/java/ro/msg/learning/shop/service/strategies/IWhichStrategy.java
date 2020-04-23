package ro.msg.learning.shop.service.strategies;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.ProductQuantityDto;

import java.util.List;

@Service
public interface IWhichStrategy {
    List<Stock> getProductLocation(final Integer productId, final Integer quantity);
}
