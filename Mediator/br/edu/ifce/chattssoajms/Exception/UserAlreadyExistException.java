package br.edu.ifce.chattssoajms.Exception;

/**
 * Created by jp-desktop on 13/04/2015.
 */
public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message){
        super(message);
    }
}
