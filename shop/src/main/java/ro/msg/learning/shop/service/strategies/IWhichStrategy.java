package ro.msg.learning.shop.service.strategies;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Stock;

import java.util.List;
import java.util.Map;

@Service
public interface IWhichStrategy {
    List<Stock> getProductLocation(Map<Integer, Integer> productQuantityListInput);
}
