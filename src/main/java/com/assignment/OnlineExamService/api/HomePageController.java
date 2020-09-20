package com.assignment.OnlineExamService.api;

import com.assignment.OnlineExamService.models.Examiner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shubham sharma
 *         <p>
 *         19/09/20
 */
@Controller
@RequestMapping(value = { "/", "/home" })
public class HomePageController {
    
    @GetMapping
    public String homePage(Authentication authentication, Model model){
        Examiner examiner = (Examiner) authentication.getPrincipal();
        model.addAttribute("username", examiner.getUsername());
        return "home.html";
    }
}
