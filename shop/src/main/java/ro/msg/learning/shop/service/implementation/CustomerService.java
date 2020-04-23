package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.CustomerConverter;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;


    public Customer getCustomer(final Integer customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("No customer with this id" + customerId));
    }
}
