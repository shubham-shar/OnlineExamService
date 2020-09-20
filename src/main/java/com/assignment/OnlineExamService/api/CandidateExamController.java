package com.assignment.OnlineExamService.api;

import java.util.List;

import com.assignment.OnlineExamService.models.Result;
import com.assignment.OnlineExamService.models.presentations.QuestionDto;
import com.assignment.OnlineExamService.services.CandidateExamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/exam")
public class CandidateExamController {
    
    @Autowired
    CandidateExamsService candidateExamsService;
    
    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionDto>> fetchTest(@PathVariable Long id, @RequestParam String email,
            @RequestParam String password, @RequestParam String testName){
        List<QuestionDto> questions = candidateExamsService.getTestQuestion(id, email, password, testName);
        return ResponseEntity.ok(questions);
    }
    
    @PostMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Result> submitTest(@PathVariable Long id, @RequestParam String email,
            @RequestParam String password, @RequestParam String testName,
            @RequestBody List<QuestionDto> questions) {
        Result result = candidateExamsService.submitTest(id, email, password, testName, questions);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping
    public ResponseEntity<Result> fetchResult(@RequestParam String email, @RequestParam String testName){
        Result result = candidateExamsService.getResult(email, testName);
        return ResponseEntity.ok(result);
    }
}
