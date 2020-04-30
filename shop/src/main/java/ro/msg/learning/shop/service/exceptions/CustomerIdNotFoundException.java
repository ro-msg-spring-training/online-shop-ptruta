package ro.msg.learning.shop.service.exceptions;

public class CustomerIdNotFoundException extends RuntimeException {

    public CustomerIdNotFoundException(String message){
        super(message);
    }
}
