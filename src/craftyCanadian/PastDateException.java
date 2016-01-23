package craftyCanadian;

/**
 * Custom Exception
 *
 */

public class PastDateException extends Exception {

    private String message = null;
    
    public PastDateException() {
        super();
    }
 
    public PastDateException(String message) {
        super(message);
        this.message = message;
    }
 
    public PastDateException(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
}
