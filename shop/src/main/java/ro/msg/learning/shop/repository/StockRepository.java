package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.StockKey;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, StockKey> {
    List<Stock> findStockByProduct(Product product);

    List<Stock> findStockByLocation(Location location);

    List<Stock> findStockByProductId(Integer productId);
}
