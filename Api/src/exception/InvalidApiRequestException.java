package exception;

/**
 * Created by aaron on 5/27/2018.
 */
public class InvalidApiRequestException extends Exception {
    public InvalidApiRequestException() {
            this("Invalid Request");
        }

    public InvalidApiRequestException(String message) {
            super (message);
    }

    public InvalidApiRequestException(PlayerNotFound playerNotFound) {
        super(playerNotFound);
    }
}
