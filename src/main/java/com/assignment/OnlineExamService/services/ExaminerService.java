package com.assignment.OnlineExamService.services;

import java.util.Objects;
import java.util.Optional;

import com.assignment.OnlineExamService.models.Examiner;
import com.assignment.OnlineExamService.repository.ExaminerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shubham sharma
 *         <p>
 *         19/09/20
 */
@Slf4j
@Service
public class ExaminerService implements UserDetailsService {

    @Autowired
    private ExaminerRepository examinerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Examiner> examiner = examinerRepository.findByUsername(username);
        if (examiner.isEmpty()) {
            throw new UsernameNotFoundException(String.format("examiner not found with username = %s", username));
        }
        return examiner.get();
    }

    public Examiner register(Examiner examiner) throws Exception {
        String encodedPW = passwordEncoder.encode(examiner.getPassword());
        examiner.setPassword(encodedPW);
        examiner.setEnabled(true);
        examiner.setRole("USER");
        try {
            examiner = examinerRepository.save(examiner);
        } catch (Exception e) {
            throw e;
        }
        return examiner;
    }

    public Boolean isUserRegistered(String  username){
        return examinerRepository.findByUsername(username).isPresent();
    }
}
