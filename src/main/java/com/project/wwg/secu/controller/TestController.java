package com.project.wwg.secu.controller;

import com.project.wwg.secu.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
    private static final Log LOG = LogFactory.getLog(TestController.class);

    TestService testService;

    TestController(TestService testService){
        this.testService = testService;
    }

    @GetMapping("/login")
    public String login(Model model){
        LOG.info("hello");
        model.addAttribute("content","testSuccess");
        return "secu/login";
    }

    @GetMapping("users")
    public String getUsers(
            Model model,
            @RequestParam("user")String user){
        LOG.info("hello user");
        model.addAttribute("user",testService.getUsers(user));
        return "secu/dbTest";
    }
}
