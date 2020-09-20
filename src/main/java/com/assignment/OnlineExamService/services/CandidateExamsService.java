package com.assignment.OnlineExamService.services;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.assignment.OnlineExamService.exceptions.EntityAlreadyExistsException;
import com.assignment.OnlineExamService.exceptions.EntityNotFoundException;
import com.assignment.OnlineExamService.exceptions.UnauthorizedException;
import com.assignment.OnlineExamService.models.Candidate;
import com.assignment.OnlineExamService.models.CandidateExams;
import com.assignment.OnlineExamService.models.Questions;
import com.assignment.OnlineExamService.models.Result;
import com.assignment.OnlineExamService.models.Tests;
import com.assignment.OnlineExamService.models.presentations.ExamDto;
import com.assignment.OnlineExamService.models.presentations.QuestionDto;
import com.assignment.OnlineExamService.repository.CandidateExamsRepository;
import com.assignment.OnlineExamService.repository.CandidateRepository;
import com.assignment.OnlineExamService.repository.QuestionsRepository;
import com.assignment.OnlineExamService.repository.ResultRepository;
import com.assignment.OnlineExamService.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
@Slf4j
@Service
public class CandidateExamsService {
    
    @Autowired
    CandidateExamsRepository candidateExamsRepository;
    
    @Autowired
    TestRepository testRepository;
    
    @Autowired
    CandidateRepository candidateRepository;
    
    @Autowired
    QuestionsRepository questionsRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    ResultRepository resultRepository;
    
    public void assignExam(ExamDto dto) {
        String email = dto.getEmail();
        String testName = dto.getTestName();
        Tests test = testRepository.findByTestName(testName).orElseThrow(
                () -> new EntityNotFoundException("Test with name " + testName + " not found"));
        Candidate candidate = candidateRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Candidate with email " + email + " not found"));
        
        Optional<CandidateExams> optionalExams = candidateExamsRepository.findByPassword(dto.getPassword());
        if (optionalExams.isPresent()) {
            throw new EntityAlreadyExistsException(
                    "Password " + dto.getPassword() + " already exists for " + "a candidate for another test");
        }
        
        if (Objects.nonNull(candidate.getTests())) {
            candidate.getTests().add(test);
        } else {
            candidate.setTests(Collections.singletonList(test));
        }
        candidate = candidateRepository.save(candidate);
        
        if (Objects.nonNull(test.getCandidates())) {
            test.getCandidates().add(candidate);
        } else {
            test.setCandidates(Collections.singletonList(candidate));
        }
        test = testRepository.save(test);
        
        CandidateExams candidateExamToPersist = new CandidateExams();
        candidateExamToPersist.setPassword(passwordEncoder.encode(dto.getPassword()));
        candidateExamToPersist.setCandidate(candidate);
        candidateExamToPersist.setTest(test);
        candidateExamsRepository.save(candidateExamToPersist);
    }
    
    public List<QuestionDto> getTestQuestion(Long id, String email, String password, String testName) {
        CandidateExams exam = validRequest(id, email, password, testName);
        return exam.getTest().getQuestions().stream()
            .map(questions -> {
                QuestionDto dto = new QuestionDto();
                dto.setId(questions.getId());
                dto.setOptions(questions.getOptions());
                dto.setQuestion(questions.getQuestion());
                return dto;
            }).collect(Collectors.toList());
    }
    
    public Result submitTest(Long id, String email, String password, String testName,
            List<QuestionDto> questions) {
        CandidateExams exam = validRequest(id, email, password, testName);
        boolean isQuestionsSorted = questions.stream()
                                             .sorted(Comparator.comparing(QuestionDto::getSubmitTime))
                                             .collect(Collectors.toList()).equals(questions);
        if(!isQuestionsSorted){
            throw new UnauthorizedException("Questions not attempted in order");
        }
        
        int score = 0;
        
        for (QuestionDto question : questions) {
            Optional<Questions> optionalQuestion = questionsRepository.findById(question.getId());
            if (optionalQuestion.isEmpty()) {
                throw new EntityNotFoundException("question with id " + question.getId() + " not found");
            }
            if (optionalQuestion.get().getAnswer().equals(question.getAnswer())) {
                score+=5;
            }
        }
        Result result = new Result();
        result.setScore(BigDecimal.valueOf(score));
        result.setExaminer(exam.getTest().getExaminer());
        result.setTest(exam.getTest());
        result.setEmail(email);
        result.setCandidate(exam.getCandidate());
        exam.setIsCompleted(true);
        candidateExamsRepository.save(exam);
        return resultRepository.save(result);
    }
    
    private CandidateExams validRequest(Long id, String email, String password, String testName) {
        Tests test = testRepository.findByTestName(testName).orElseThrow(
                () -> new EntityNotFoundException("Test with name " + testName + " not found"));
        Candidate candidate = candidateRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Candidate with email " + email + " not found"));
        CandidateExams exam = candidateExamsRepository.findById(id)
                                                      .orElseThrow(() -> new UnauthorizedException(
                                                                       "Exam " + id + "not found"));
        if(Boolean.TRUE.equals(exam.getIsCompleted())){
            throw new UnauthorizedException("Exam " + test.getTestName() + " already attempted");
        }
        if (!passwordEncoder.matches(password, exam.getPassword())) {
            throw new UnauthorizedException("Password " + password + " not valid");
        }
        return exam;
    }
    
    public Result getResult(String email, String testName) {
        Tests test = testRepository.findByTestName(testName).orElseThrow(
                () -> new EntityNotFoundException("Test with name " + testName + " not found"));
        Candidate candidate = candidateRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Candidate with email " + email + " not found"));
        Optional<Result> optionalResult = resultRepository.findByEmailAndTestId(email, test.getId());
        return optionalResult.orElseThrow(
                () -> new EntityNotFoundException("result not found for email " + email + " and test " + testName));
    }
}
