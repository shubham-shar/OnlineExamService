package com.assignment.OnlineExamService.models.presentations;

import java.util.List;

import com.assignment.OnlineExamService.models.Questions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {
    
    private Long id;
    
    private String testName;
   
    private List<Questions> questions;
}
