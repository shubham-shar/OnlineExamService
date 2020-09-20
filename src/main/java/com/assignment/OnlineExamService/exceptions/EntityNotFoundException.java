package com.assignment.OnlineExamService.exceptions;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
