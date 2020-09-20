package com.assignment.OnlineExamService.repository;

import java.util.Optional;

import com.assignment.OnlineExamService.models.Tests;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
public interface TestRepository extends JpaRepository<Tests, Long> {
    public Optional<Tests> findByTestName(String name);
}
