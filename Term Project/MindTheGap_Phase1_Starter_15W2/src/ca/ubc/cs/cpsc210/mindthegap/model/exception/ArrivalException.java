package ca.ubc.cs.cpsc210.mindthegap.model.exception;

/*
 * Represents exception raised when errors occur with arrival estimates
 */
public class ArrivalException extends Exception {
    public ArrivalException() {
        super();
    }

    public ArrivalException(String msg) {
        super(msg);
    }
}
