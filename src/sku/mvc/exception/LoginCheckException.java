package sku.mvc.exception;

public class LoginCheckException extends RuntimeException{
	public LoginCheckException() {}
	public LoginCheckException(String message) {
		super(message);
	}
}