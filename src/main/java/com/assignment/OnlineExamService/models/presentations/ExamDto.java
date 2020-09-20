package com.assignment.OnlineExamService.models.presentations;

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
public class ExamDto {
    private String password;
    private String email;
    private String testName;
}
