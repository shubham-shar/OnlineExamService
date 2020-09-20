package com.assignment.OnlineExamService.exceptions;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
