package com.assignment.OnlineExamService.repository;

import java.util.Optional;

import com.assignment.OnlineExamService.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);
}

