package com.project.wwg.secu.controller;

import com.project.wwg.secu.service.TestService;
import com.project.wwg.secu.service.UserSignUpService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecuController {
    private static final Log LOG = LogFactory.getLog(SecuController.class);

    TestService testService;
    UserSignUpService userSignUpService;

    SecuController(TestService testService,UserSignUpService userSignUpService){
        this.testService = testService;
        this.userSignUpService = userSignUpService;
    }

    @GetMapping("/loginPage")
    public String login(Model model){
        LOG.info("hello");
        model.addAttribute("content","testSuccess");
        return "secu/login";
    }
    @PostMapping("/userSignUpProcess")
    public String userSignUp(Model model,
                             @RequestParam(name = "username") String username,
                             @RequestParam(name = "password") String password)
    {
        LOG.info(username +", "+ password);
        if(userSignUpService.userSignUp(username, password)){
            LOG.info("signsUp success");
            return"home";
        }
        else {
            LOG.info("signsUp fail");
            return "redirect:/userSignUp";//나중에 적절한 페이지로 이동
        }
    }

    @GetMapping("/userSignUp")
    public String userSignUpPage(Model model){
        return"secu/userSignUp";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/all")
    public String all() {
        return "secu/all";
    }

    @GetMapping("/test")
    public String test() {
        return "secu/dbTest";
    }
}
