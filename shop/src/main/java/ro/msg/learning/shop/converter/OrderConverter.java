package ro.msg.learning.shop.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.service.implementation.CustomerService;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter extends BaseConverter<Order, OrderDto> {
    private final CustomerService customerService;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public Order convertDtoToModel(OrderDto dto) {
        Customer customer = customerService.getCustomer(dto.getCustomerId());
        return Order.builder()
                .id(dto.getId())
                .customer(customer)
                .localDateTime(dto.getLocalDateTime())
                .addressCity(dto.getCity())
                .addressCountry(dto.getCountry())
                .addressCounty(dto.getCounty())
                .addressStreetAddress(dto.getStreetAddress())
                .build();
    }

    @Override
    public OrderDto convertModelToDto(Order order) {
        Integer customerId = Optional.ofNullable(order.getCustomer())
                .map(Customer::getId)
                .orElse(null);
        Map<Integer, Integer> orderDetails = orderDetailRepository.findAll()
                .stream()
                .filter(o -> o.getOrder().equals(order))
                .collect(Collectors.toMap(orderDetail -> orderDetail.getProduct().getId(), OrderDetail::getQuantity));
        return OrderDto.builder()
                .id(order.getId())
                .city(order.getAddressCity())
                .country(order.getAddressCountry())
                .county(order.getAddressCounty())
                .customerId(customerId)
                .streetAddress(order.getAddressStreetAddress())
                .localDateTime(order.getLocalDateTime())
                .orderDetails(orderDetails)
                .build();
    }
}
