package ro.msg.learning.shop.service.exceptions;

public class UserNameNotFound extends RuntimeException {

    public UserNameNotFound(String message){
        super(message);
    }
}
