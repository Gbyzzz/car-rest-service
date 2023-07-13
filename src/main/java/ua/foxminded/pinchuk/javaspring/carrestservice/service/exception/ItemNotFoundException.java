package ua.foxminded.pinchuk.javaspring.carrestservice.service.exception;

public class ItemNotFoundException extends Exception {

    public ItemNotFoundException(){
        super();
    }
    public ItemNotFoundException(String message){
        super(message);
    }
    public ItemNotFoundException(Exception e){
        super(e);
    }
    public ItemNotFoundException(String message, Exception e){
        super(message, e);
    }

}
