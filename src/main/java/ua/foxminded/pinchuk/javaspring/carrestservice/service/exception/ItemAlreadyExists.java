package ua.foxminded.pinchuk.javaspring.carrestservice.service.exception;

public class ItemAlreadyExists extends Exception {
    public ItemAlreadyExists(String message){
        super(message);
    }
}
