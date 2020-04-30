package ro.msg.learning.shop.service.exceptions;

public class SupplierIdNotFoundException extends RuntimeException{

    public SupplierIdNotFoundException(String message){
        super(message);
    }
}
