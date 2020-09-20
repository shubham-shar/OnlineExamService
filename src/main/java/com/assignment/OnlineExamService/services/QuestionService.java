package com.assignment.OnlineExamService.services;

import java.util.Optional;

import com.assignment.OnlineExamService.exceptions.EntityAlreadyExistsException;
import com.assignment.OnlineExamService.exceptions.EntityNotFoundException;
import com.assignment.OnlineExamService.models.Candidate;
import com.assignment.OnlineExamService.models.Questions;
import com.assignment.OnlineExamService.repository.CandidateRepository;
import com.assignment.OnlineExamService.repository.QuestionsRepository;
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
public class QuestionService {
    
    @Autowired
    QuestionsRepository questionsRepository;
    
    public Questions fetchQuestion(Long id){
        return questionsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No question found with id " + id));
    }
}
