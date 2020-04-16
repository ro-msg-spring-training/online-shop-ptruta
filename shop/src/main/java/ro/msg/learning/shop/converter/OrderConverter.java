package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.dto.OrderDto;

@Component
public class OrderConverter extends BaseConverter<Order, OrderDto> {

    @Override
    public Order convertDtoToModel(OrderDto dto) {
        Order order = Order.builder()
                           .addressCity(dto.getCity())
                           .addressCountry(dto.getCountry())
                           .addressCounty(dto.getCounty())
                           .addressStreetAddress(dto.getStreetAddress())
                           .customer(dto.getCustomerId())
                           .localDateTime(dto.getLocalDateTime())
                           .shippedForm(dto.getShippedFormId())
                           .build();
        order.setId(dto.getId());
        return order;
    }

    @Override
    public OrderDto convertModelToDto(Order order) {
        OrderDto orderDto = OrderDto.builder()
                                    .city(order.getAddressCity())
                                    .country(order.getAddressCountry())
                                    .county(order.getAddressCounty())
                                    .customerId(order.getCustomer())
                                    .shippedFormId(order.getShippedForm())
                                    .streetAddress(order.getAddressStreetAddress())
                                    .localDateTime(order.getLocalDateTime())
                                    .build();
        orderDto.setId(order.getId());
        return orderDto;
    }
}
