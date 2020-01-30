package org.dominisoft.scrumdev.claro2020.domain.exceptions;

/**
 * @author jose an rodriguez
 */
public class DocumentInvalidException extends RuntimeException {
    public DocumentInvalidException(String message) {
        super(message);
    }

    public DocumentInvalidException() {
        super();
    }
}
