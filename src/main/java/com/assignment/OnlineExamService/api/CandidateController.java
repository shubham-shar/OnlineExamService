package com.assignment.OnlineExamService.api;

import com.assignment.OnlineExamService.models.Candidate;
import com.assignment.OnlineExamService.services.CandidateService;
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
@RequestMapping("/candidate")
public class CandidateController {
    
    @Autowired
    CandidateService candidateService;
    
    @PostMapping
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate){
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.saveCandidate(candidate));
    }
    
    @GetMapping
    public ResponseEntity<Candidate> getCandidate(@RequestParam String email){
        return ResponseEntity.status(HttpStatus.OK).body(candidateService.fetchCandidate(email));
    }
    
}
