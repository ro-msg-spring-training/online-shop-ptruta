package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.service.implementation.OrderService;
import ro.msg.learning.shop.service.strategies.ChosenStrategy;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ChosenStrategy strategy;

    @PostMapping(value = "/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody final OrderDto orderDto,
                                @RequestBody final Integer productId,
                                @RequestBody final Integer quantity) {

        List<Stock> stocksList = strategy.getStrategy().getProductLocation(productId, quantity);

        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();

        for (Stock stock : stocksList){
            orderDetailDtos.add(new OrderDetailDto(stock.getProduct().getId(),stock.getQuantity()));
        }

        return null;
    }
}