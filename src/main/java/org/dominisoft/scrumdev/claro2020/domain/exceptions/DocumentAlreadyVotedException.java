package org.dominisoft.scrumdev.claro2020.domain.exceptions;

/**
 * @author julio rodriguez
 *
 */
public class DocumentAlreadyVotedException extends RuntimeException{
    public DocumentAlreadyVotedException(String message) {
        super(message);
    }

    public DocumentAlreadyVotedException() {
        super();
    }
}
