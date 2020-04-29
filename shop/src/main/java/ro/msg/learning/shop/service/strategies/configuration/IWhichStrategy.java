package ro.msg.learning.shop.service.strategies.configuration;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;
import ro.msg.learning.shop.service.exceptions.StockLocationProductIdNotFoundException;

import java.util.List;
import java.util.Map;

@Service
public interface IWhichStrategy {
    List<Stock> getProductLocation(Map<Integer, Integer> productQuantityListInput);
}
