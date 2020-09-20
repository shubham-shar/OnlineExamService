package com.assignment.OnlineExamService.api;

import java.util.Objects;

import com.assignment.OnlineExamService.models.Examiner;
import com.assignment.OnlineExamService.services.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author shubham sharma
 *         <p>
 *         19/09/20
 */
@Controller
public class SignupController {
    @Autowired
    ExaminerService examinerService;
    
    @GetMapping("/signup")
    public String homePage(){
        return "signup.html";
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute("SpringWeb") Examiner examiner, Model model) {
        
        if (Objects.isNull(examiner)) {
            model.addAttribute("nullUser",true);
            return "signup.html";
        }
        
        if(examinerService.isUserRegistered(examiner.getUsername())){
            model.addAttribute("isRegistered",true);
            return "signup.html";
        }
        
        try {
            examinerService.register(examiner);
            model.addAttribute("registered",true);
            return "login.html";
        } catch (Exception e) {
            model.addAttribute("error",true);
            return "signup.html";
        }
    }
}
