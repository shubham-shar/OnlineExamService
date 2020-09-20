package com.assignment.OnlineExamService.repository;

import com.assignment.OnlineExamService.models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shubham sharma
 *         <p>
 *         20/09/20
 */
public interface QuestionsRepository extends JpaRepository<Questions, Long> {}
