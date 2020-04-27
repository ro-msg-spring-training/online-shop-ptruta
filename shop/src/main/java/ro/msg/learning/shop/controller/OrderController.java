package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.converter.OrderConverter;
import ro.msg.learning.shop.converter.StockConverter;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductQuantityDto;
import ro.msg.learning.shop.service.exceptions.*;
import ro.msg.learning.shop.service.implementation.LocationService;
import ro.msg.learning.shop.service.implementation.OrderService;
import ro.msg.learning.shop.service.implementation.ProductService;
import ro.msg.learning.shop.service.implementation.StockService;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final StockConverter stockConverter;
    private final LocationService locationService;
    private final StockService stockService;
    private final OrderConverter orderConverter;

    @PostMapping(value = "/orders",  produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws StockLocationProductIdNotFoundException, ProductNoIdFoundException, LocationIdNotFoundException, OrderNotFoundIdException, UnavailableStockException {
        Map<Integer, Integer> stocks = new HashMap<>();

        List<ProductQuantityDto> orderDetailDtos = orderDto.getOrderDetails();

        for (ProductQuantityDto orderDetailDto : orderDetailDtos) {

            Product product = productService.getProduct(orderDetailDto.getProductId());

            Integer quantity = orderDetailDto.getQuantity();

            stocks.put(product.getId(), quantity);
        }

        Order order = orderConverter.convertDtoToModel(orderDto);

        order = orderService.createOrder(stocks, order);

        OrderDto orderConverted = orderConverter.convertModelToDto(order);

        orderConverted.setId(orderDto.getId());

        return ResponseEntity.accepted().body(orderConverted);
    }
}