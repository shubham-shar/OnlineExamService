package com.assignment.OnlineExamService.repository;

import java.util.Optional;

import com.assignment.OnlineExamService.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
public interface ResultRepository extends JpaRepository<Result, Long> {
    Optional<Result> findByEmailAndTestId(String email, Long testId);
}
