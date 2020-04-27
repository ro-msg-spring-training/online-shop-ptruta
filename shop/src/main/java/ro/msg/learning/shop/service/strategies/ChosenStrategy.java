package ro.msg.learning.shop.service.strategies;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.implementation.LocationService;
import ro.msg.learning.shop.service.implementation.StockService;

@Configuration
@RequiredArgsConstructor
public class ChosenStrategy {
    private final StockService stockService;
    private final LocationService locationService;

    @Value("${choose_strategy}")
    private String chooseStrategy;

    @Bean
    public IWhichStrategy getStrategy() {
        if (chooseStrategy.equals("MostAbundant")) {
            return new MostAbundantStrategy(stockService, locationService);
        } else if (chooseStrategy.equals("Single"))
            return new SingleLocationStrategy(stockService, locationService);

        return null;
    }
}
