package exception;

public class NoInternetConnectionException extends Exception {

    public NoInternetConnectionException() { }

    public NoInternetConnectionException(String message) {
        super(message);
    }

    public NoInternetConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

}
