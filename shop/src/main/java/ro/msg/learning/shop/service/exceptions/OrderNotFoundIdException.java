package ro.msg.learning.shop.service.exceptions;

public class OrderNotFoundIdException extends RuntimeException {

    public OrderNotFoundIdException(String message){
        super(message);
    }
}
