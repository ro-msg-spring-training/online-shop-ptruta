package ro.msg.learning.shop.service.exceptions;

public class LocationIdNotFoundException extends RuntimeException {

    public LocationIdNotFoundException(String message){
        super(message);
    }
}
