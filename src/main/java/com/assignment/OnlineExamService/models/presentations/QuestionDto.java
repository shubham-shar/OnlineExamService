package com.assignment.OnlineExamService.models.presentations;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private Long id;
    
    private String question;
    
    private HashMap<Integer, String> options;
    
    private Integer answer;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp submitTime;
}
