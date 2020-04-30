package ro.msg.learning.shop.service.exceptions;

public class StrategyNotFoundException extends RuntimeException {

    public StrategyNotFoundException(String message){
        super(message);
    }
}
