package ro.msg.learning.shop.service.exceptions;

public class UnavailableStockException extends Exception {

    public UnavailableStockException(String message){
        super(message);
    }
}
