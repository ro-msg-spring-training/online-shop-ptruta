package ro.msg.learning.shop.service.strategies;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

@Configuration
@RequiredArgsConstructor
public class ChosenStrategy {
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Value("${choose_strategy}")
    private String chooseStrategy;

    @Bean
    public IWhichStrategy getStrategy() {
        if (chooseStrategy.equals("MostAbundant")) {
            return new MostAbundantStrategy(stockRepository, productRepository);
        } else if (chooseStrategy.equals("Single"))
            return new SingleLocationStrategy(stockRepository);

        return null;
    }
}
