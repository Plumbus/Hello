/**
 * Created by gerasimov on 05.08.2015.
 */

public class MyException extends Exception {

    private static final long serialVersionUID = 1997753363232807009L;

    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(String message, Throwable cause,
                       boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

