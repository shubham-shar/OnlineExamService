package com.assignment.OnlineExamService.api;

import com.assignment.OnlineExamService.models.Candidate;
import com.assignment.OnlineExamService.models.Questions;
import com.assignment.OnlineExamService.services.CandidateService;
import com.assignment.OnlineExamService.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    
    @Autowired
    QuestionService questionService;
    
    @GetMapping
    public ResponseEntity<Questions> getCandidate(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(questionService.fetchQuestion(id));
    }
    
}
