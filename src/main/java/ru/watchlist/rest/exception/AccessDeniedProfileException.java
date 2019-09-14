package ru.watchlist.rest.exception;


import org.springframework.security.access.AccessDeniedException;

public class AccessDeniedProfileException extends AccessDeniedException {

    public AccessDeniedProfileException(String msg) {
        super(msg);
    }

    public AccessDeniedProfileException(String msg, Throwable t) {
        super(msg, t);
    }
}
