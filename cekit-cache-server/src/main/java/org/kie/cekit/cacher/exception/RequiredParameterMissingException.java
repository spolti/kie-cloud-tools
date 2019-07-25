package org.kie.cekit.cacher.exception;

public class RequiredParameterMissingException extends RuntimeException {

    public RequiredParameterMissingException(String message) {
        super(message);
    }

    public RequiredParameterMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
