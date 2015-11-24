package com.sixamigos.sjsucanvasapp.login.canvas;

/**
 * Created by christopherbachner on 11/6/15.
 */
public class CanvasLoginFailureException extends Exception {

    public CanvasLoginFailureException() {
        super();
    }

    public CanvasLoginFailureException(String detailMessage) {
        super(detailMessage);
    }

    public CanvasLoginFailureException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CanvasLoginFailureException(Throwable throwable) {
        super(throwable);
    }
}
