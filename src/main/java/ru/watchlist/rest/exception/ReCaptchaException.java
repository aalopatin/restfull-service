package ru.watchlist.rest.exception;

public class ReCaptchaException extends Exception {
    public ReCaptchaException(String msg) {
        super(msg);
    }

    public ReCaptchaException(String msg, Throwable t) {
        super(msg, t);
    }
}
