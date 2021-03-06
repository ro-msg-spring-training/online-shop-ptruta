package ro.msg.learning.shop.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductQuantityDto;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.service.implementation.CustomerService;
import ro.msg.learning.shop.service.implementation.OrderDetailService;
import ro.msg.learning.shop.service.implementation.OrderService;
import ro.msg.learning.shop.service.implementation.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter extends BaseConverter<Order, OrderDto> {
    private final CustomerService customerService;
    private final LocationConverter locationConverter;
    private final OrderDetailService orderDetailService;

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
        Map<Integer, Integer> orderDetails = orderDetailService.getOrderDetails()
                .stream()
                .filter(o -> o.getOrder().getId().equals(order.getId()))
                .collect(Collectors.toMap(orderDetail -> orderDetail.getProduct().getId(),
                        OrderDetail::getQuantity));
        List<ProductQuantityDto> orderDetailsList = new ArrayList<>();
        for (Map.Entry<Integer,Integer> orderDetail : orderDetails.entrySet()){
            ProductQuantityDto productQuantityDto = new ProductQuantityDto();
            productQuantityDto.setProductId(orderDetail.getKey());
            productQuantityDto.setQuantity(orderDetail.getValue());
            orderDetailsList.add(productQuantityDto);
        }
        return OrderDto.builder()
                .id(order.getId())
                .city(order.getAddressCity())
                .country(order.getAddressCountry())
                .county(order.getAddressCounty())
                .customerId(customerId)
                .streetAddress(order.getAddressStreetAddress())
                .localDateTime(order.getLocalDateTime())
                .orderDetails(orderDetailsList)
                .shippedFrom(locationConverter.convertModelToDto(order.getShippedForm()))
                .build();
    }
}
