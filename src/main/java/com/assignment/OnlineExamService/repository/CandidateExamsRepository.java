package com.assignment.OnlineExamService.repository;

import java.util.Optional;

import com.assignment.OnlineExamService.models.CandidateExams;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
public interface CandidateExamsRepository extends JpaRepository<CandidateExams, Long> {
    Optional<CandidateExams> findByPassword(String password);
}
