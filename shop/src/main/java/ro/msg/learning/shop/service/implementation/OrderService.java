package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;
import ro.msg.learning.shop.service.exceptions.ProductNoIdFoundException;
import ro.msg.learning.shop.service.exceptions.StockLocationProductIdNotFoundException;
import ro.msg.learning.shop.service.exceptions.UnavailableStockException;
import ro.msg.learning.shop.service.strategies.ChosenStrategy;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderDetailRepository orderDetailRepository;
    private final ChosenStrategy strategy;
    private final StockService stockService;
    private final CustomerService customerService;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Map<Integer, Integer> stocks, Order order) throws StockLocationProductIdNotFoundException,
            LocationIdNotFoundException, UnavailableStockException {
        List<Stock> stocksList = strategy.getStrategy().getProductLocation(stocks);
        List<OrderDetail> orderDetails = new ArrayList<>();
        Stock newSock;
        OrderDetail orderDetail;

        Order orderNew = Order.builder()
                .localDateTime(order.getLocalDateTime())
                .shippedForm(stocksList.get(0).getLocation())
                .customer(customerService.getCustomer(order.getCustomer().getId()))
                .addressCountry(order.getAddressCountry())
                .addressCity(order.getAddressCity())
                .addressCounty(order.getAddressCounty())
                .addressStreetAddress(order.getAddressStreetAddress())
                .build();

        Order savedOrder = orderRepository.save(orderNew);
        LOGGER.info("Total number of orders: {}", orderRepository.findAll().size());

        for (Stock stock : stocksList) {
            Stock stockWithUpdatedQuantity = updateStockQuantity(stock, stocks);

            if (stockWithUpdatedQuantity == null) {
                orderRepository.deleteById(savedOrder.getId());
                throw new UnavailableStockException("Product " + stock.getProduct() +
                        " doesn't have in stock " + stock.getQuantity() + " items");
            } else { // Add the valid products in a list
                // Create the id for order detail
                Product product = stockWithUpdatedQuantity.getProduct();
                Integer quantity = stockWithUpdatedQuantity.getQuantity();
                OrderDetailKey orderDetailKey = OrderDetailKey
                        .builder().orderId(savedOrder.getId())
                        .productId(product.getId()).build();

                // Create the order detail
                orderDetail = OrderDetail.builder()
                        .id(orderDetailKey)
                        .order(savedOrder)
                        .product(product)
                        .quantity(quantity).build();

                // Add it to the list
                orderDetails.add(orderDetail);
            }
        }
        // If the order has all products in stock save it
        for (OrderDetail o : orderDetails) {
            orderDetailRepository.save(o);
        }

        return orderNew;
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
