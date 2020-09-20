package com.assignment.OnlineExamService.models.error;

import java.util.Date;

import org.springframework.http.HttpStatus;
import lombok.Data;

/**
 * @author shubham sharma
 *         <p>
 *         19/09/20
 */
@Data
public class ErrorDetails {
    private HttpStatus status;
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(HttpStatus status, Date timestamp, String message, String details) {
        super();
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
