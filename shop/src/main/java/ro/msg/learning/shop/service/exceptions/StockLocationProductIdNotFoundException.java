package ro.msg.learning.shop.service.exceptions;

public class StockLocationProductIdNotFoundException extends RuntimeException {

    public StockLocationProductIdNotFoundException(String message){
        super(message);
    }
}
