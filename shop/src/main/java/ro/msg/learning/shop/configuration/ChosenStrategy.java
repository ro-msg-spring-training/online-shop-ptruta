package ro.msg.learning.shop.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.implementation.LocationService;
import ro.msg.learning.shop.service.implementation.StockService;
import ro.msg.learning.shop.service.strategies.IWhichStrategy;
import ro.msg.learning.shop.service.strategies.MostAbundantStrategy;
import ro.msg.learning.shop.service.strategies.SingleLocationStrategy;

@Configuration
@RequiredArgsConstructor
public class ChosenStrategy {

    @Value("${choose_strategy}")
    private String chooseStrategy;

    @Bean
    public IWhichStrategy getStrategy(StockService stockService, LocationService locationService) {
        if (chooseStrategy.equals("MostAbundant")) {
            return new MostAbundantStrategy(stockService);
        } else if (chooseStrategy.equals("Single"))
            return new SingleLocationStrategy(stockService, locationService);

        return null;
    }
}
