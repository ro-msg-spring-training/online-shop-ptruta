package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.service.exceptions.LocationIdNotFoundException;
import ro.msg.learning.shop.service.exceptions.OrderNotFoundIdException;
import ro.msg.learning.shop.service.exceptions.ProductNoIdFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    @Transactional
    public OrderDetail createDetail(OrderDetail orderDetail, Order order) {
        productRepository.findById(orderDetail.getProduct().getId())
                .orElseThrow(() -> new ProductNoIdFoundException("No product id found" + orderDetail.getProduct().getId()));
        return orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> getOrderDetails(){
        return orderDetailRepository.findAll();
    }
}
