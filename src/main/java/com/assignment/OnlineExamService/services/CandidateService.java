package com.assignment.OnlineExamService.services;

import java.util.Collections;
import java.util.Optional;

import com.assignment.OnlineExamService.exceptions.EntityAlreadyExistsException;
import com.assignment.OnlineExamService.exceptions.EntityNotFoundException;
import com.assignment.OnlineExamService.models.Candidate;
import com.assignment.OnlineExamService.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
@Slf4j
@Service
public class CandidateService {
    
    @Autowired
    CandidateRepository candidateRepository;
    
    public Candidate saveCandidate(Candidate candidate){
        Optional<Candidate> optionalCandidate = candidateRepository.findByEmail(candidate.getEmail());
        if (optionalCandidate.isPresent() ) {
            throw new EntityAlreadyExistsException("Candidate with email " + candidate.getEmail() + " already present");
        }
        candidate.setTests(Collections.emptyList());
        return candidateRepository.save(candidate);
    }
    
    public Candidate fetchCandidate(String email){
        return candidateRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("No candadate found with email " + email));
    }
}
