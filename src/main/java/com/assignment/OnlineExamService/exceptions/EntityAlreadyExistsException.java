package com.assignment.OnlineExamService.exceptions;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
public class EntityAlreadyExistsException extends RuntimeException{
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
