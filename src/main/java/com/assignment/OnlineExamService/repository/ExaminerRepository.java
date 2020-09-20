package com.assignment.OnlineExamService.repository;

import java.util.Optional;

import com.assignment.OnlineExamService.models.Examiner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shubham sharma
 *         <p>
 *         19/09/20
 */
public interface ExaminerRepository extends JpaRepository<Examiner, Long> {
    public Optional<Examiner> findByUsername(String username);
}
