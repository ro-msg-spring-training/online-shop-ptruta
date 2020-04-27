package ro.msg.learning.shop;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.msg.learning.shop.service.implementation.LocationService;
import ro.msg.learning.shop.service.implementation.StockService;
import ro.msg.learning.shop.service.strategies.SingleLocationStrategy;

@ExtendWith(SpringExtension.class)
public class MostAbundantLocationStrategyTest {

    @Mock
    private StockService stockService;
    @Mock
    private LocationService locationService;
    @InjectMocks
    private SingleLocationStrategy singleLocationStrategy;


}
