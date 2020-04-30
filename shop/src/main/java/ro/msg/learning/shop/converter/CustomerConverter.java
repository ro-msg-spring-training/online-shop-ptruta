package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.dto.CustomerDto;

@Component
public class CustomerConverter extends BaseConverter<Customer, CustomerDto> {

    @Override
    public Customer convertDtoToModel(CustomerDto dto) {
        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .userName(dto.getUserName())
                .emailAddress(dto.getEmailAddress())
                .password(dto.getPassword())
                .build();
    }

    @Override
    public CustomerDto convertModelToDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .userName(customer.getUserName())
                .emailAddress(customer.getEmailAddress())
                .password(customer.getPassword())
                .build();

    }
}
