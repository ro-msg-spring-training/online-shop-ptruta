package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.service.strategies.ChosenStrategy;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    @Value("${choose_strategy}")
    private final String chooseStrategy;

    @Transactional
    public Order createOrder(Order order, final Location location,
                             final List<Location> locations) {
        Order savedOne = null;

        if (chooseStrategy.equals("MostAbundant")) {
            savedOne = new Order(order.getId(),
                    location, order.getCustomer(),
                    order.getLocalDateTime(), order.getAddressCountry(),
                    order.getAddressCity(), order.getAddressCounty(), order.getAddressStreetAddress());
        } else if (chooseStrategy.equals("Single")) {
            for (Location loc : locations) {
                savedOne = new Order(order.getId(),
                        location, order.getCustomer(),
                        order.getLocalDateTime(), order.getAddressCountry(),
                        order.getAddressCity(), order.getAddressCounty(), order.getAddressStreetAddress());
                orderRepository.save(savedOne);
            }
        }

        return orderRepository.save(Objects.requireNonNull(savedOne));
    }
}
