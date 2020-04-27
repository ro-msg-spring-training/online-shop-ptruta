package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;
import ro.msg.learning.shop.service.exceptions.StockLocationProductIdNotFoundException;
import ro.msg.learning.shop.service.strategies.ChosenStrategy;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDetailRepository orderDetailRepository;
    private final ChosenStrategy strategy;
    private final StockService stockService;
    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    @Value("${choose_strategy}")
    private String chooseStrategy;

    @Transactional
    public List<Order> createOrder(Map<Integer, Integer> stocks, Order order) throws StockLocationProductIdNotFoundException,
            LocationIdNotFoundException {
        List<Stock> stocksList = strategy.getStrategy().getProductLocation(stocks);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail;
        List<Order> orders = new ArrayList<>();
        Order savedOrder = null;

        if (chooseStrategy.equals("Single")){
            order.setCustomer(customerService.getCustomer(1));
            order.setShippedForm(stocksList.get(0).getLocation());
            savedOrder = orderRepository.save(order);
            orders.add(savedOrder);
        }

        for (Stock stock : stocksList) {
            Stock stockWithUpdatedQuantity = updateStockQuantity(stock, stocks);

            if(chooseStrategy.equals("MostAbundant")) {
                order.setCustomer(customerService.getCustomer(1));
                order.setShippedForm(stock.getLocation());
                savedOrder = orderRepository.save(order);
                orders.add(savedOrder);
            }

            Product product = Objects.requireNonNull(stockWithUpdatedQuantity).getProduct();
            Integer quantity = stocks.get(stock.getProduct().getId());
            OrderDetailKey orderDetailKey = OrderDetailKey
                    .builder().orderId(Objects.requireNonNull(savedOrder).getId())
                    .productId(product.getId()).build();

            orderDetail = OrderDetail.builder()
                    .id(orderDetailKey)
                    .order(savedOrder)
                    .product(product)
                    .quantity(quantity).build();

            orderDetails.add(orderDetail);
        }

        orderDetailRepository.saveAll(orderDetails);

        return orders;
    }

    private Stock updateStockQuantity(Stock stock, Map<Integer, Integer> stocks)
            throws StockLocationProductIdNotFoundException {
        if (stocks.containsKey(stock.getProduct().getId())) {
            Integer quantityForProductWithId = stocks.get(stock.getProduct().getId());
            return stockService.updateStock(stock, quantityForProductWithId);
        }
        return null;
    }
}