package com.assignment.OnlineExamService.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.assignment.OnlineExamService.exceptions.EntityAlreadyExistsException;
import com.assignment.OnlineExamService.exceptions.EntityNotFoundException;
import com.assignment.OnlineExamService.exceptions.UnauthorizedException;
import com.assignment.OnlineExamService.models.Examiner;
import com.assignment.OnlineExamService.models.Questions;
import com.assignment.OnlineExamService.models.Tests;
import com.assignment.OnlineExamService.models.presentations.TestDto;
import com.assignment.OnlineExamService.repository.ExaminerRepository;
import com.assignment.OnlineExamService.repository.QuestionsRepository;
import com.assignment.OnlineExamService.repository.TestRepository;
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
public class TestService {
    
    @Autowired
    TestRepository testRepository;
    
    @Autowired
    ExaminerRepository examinerRepository;
    
    @Autowired
    QuestionsRepository questionsRepository;
    
    public TestDto addTest(TestDto testDto, Examiner examiner) {
        Optional<Tests> optionalTest = testRepository.findByTestName(testDto.getTestName());
        if (optionalTest.isPresent()){
            throw new EntityAlreadyExistsException("Test with name " + testDto.getTestName() + " already exists");
        }
    
        Examiner examinerEntity = examinerRepository.findByUsername(examiner.getUsername()).orElseThrow(
                () -> new UnauthorizedException(examiner.getUsername() + " is not authorized to create test"));
    
        Tests test = new Tests();
        test.setCandidates(Collections.emptyList());
        test.setTestName(testDto.getTestName());
        test.setExaminer(examinerEntity);
        Tests saveTest = testRepository.save(test);
        
        List<Questions> questions = testDto.getQuestions().stream().map(dto -> {
            Questions question = new Questions();
            question.setQuestion(dto.getQuestion());
            question.setAnswer(dto.getAnswer());
            question.setOptions(dto.getOptions());
            question.setTest(saveTest);
            return question;
        }).collect(Collectors.toList());
        questionsRepository.saveAll(questions);
        
        testDto.setId(saveTest.getId());
        return testDto;
    }
    
    public Tests fetchTest(String testName){
        return testRepository.findByTestName(testName).orElseThrow(
                () -> new EntityNotFoundException("No test found with name " + testName));
    }
}
