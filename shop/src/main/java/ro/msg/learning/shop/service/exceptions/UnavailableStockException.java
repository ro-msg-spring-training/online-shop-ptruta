package ro.msg.learning.shop.service.exceptions;

public class UnavailableStockException extends RuntimeException {

    public UnavailableStockException(String message){
        super(message);
    }
}
