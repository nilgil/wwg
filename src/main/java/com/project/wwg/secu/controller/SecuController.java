package com.project.wwg.secu.controller;

import com.project.wwg.secu.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecuController {
    private static final Log LOG = LogFactory.getLog(SecuController.class);

    TestService testService;

    SecuController(TestService testService){
        this.testService = testService;
    }

    @GetMapping("/loginPage")
    public String login(Model model){
        LOG.info("hello");
        model.addAttribute("content","testSuccess");
        return "secu/login";
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
}
