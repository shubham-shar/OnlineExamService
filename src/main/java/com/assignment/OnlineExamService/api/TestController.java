package com.assignment.OnlineExamService.api;

import com.assignment.OnlineExamService.models.Examiner;
import com.assignment.OnlineExamService.models.Tests;
import com.assignment.OnlineExamService.models.presentations.ExamDto;
import com.assignment.OnlineExamService.models.presentations.TestDto;
import com.assignment.OnlineExamService.services.CandidateExamsService;
import com.assignment.OnlineExamService.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    TestService testService;
    
    @Autowired
    CandidateExamsService candidateExamsService;
    
    @PostMapping
    public ResponseEntity<TestDto> addTest(@RequestBody TestDto dto,
                                            Authentication authentication){
        Examiner examiner = (Examiner) authentication.getPrincipal();
        TestDto tests = testService.addTest(dto, examiner);
        return ResponseEntity.status(HttpStatus.CREATED).body(tests);
    }
    
    @PostMapping("/assign")
    public ResponseEntity assignTest(@RequestBody ExamDto dto) {
        candidateExamsService.assignExam(dto);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<Tests> getCandidate(@RequestParam String testName){
        return ResponseEntity.status(HttpStatus.OK).body(testService.fetchTest(testName));
    }
}
