package ro.msg.learning.shop.service.exceptions;

public class ProductNoIdFoundException extends RuntimeException {

    public ProductNoIdFoundException(String message){
        super(message);
    }
}
