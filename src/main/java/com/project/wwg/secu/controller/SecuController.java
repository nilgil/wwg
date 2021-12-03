package com.project.wwg.secu.controller;

import com.project.wwg.secu.dto.UsersDto;
import com.project.wwg.secu.service.TestService;
import com.project.wwg.secu.service.UserDeleteService;
import com.project.wwg.secu.service.UserInfoUpdateService;
import com.project.wwg.secu.service.UserSignUpService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class SecuController {
    private static final Log LOG = LogFactory.getLog(SecuController.class);

    TestService testService;
    UserSignUpService userSignUpService;
    UserInfoUpdateService userInfoUpdateService;
    UserDeleteService userDeleteService;

    SecuController(TestService testService, UserSignUpService userSignUpService,
                   UserInfoUpdateService userInfoUpdateService, UserDeleteService userDeleteService){
        this.testService = testService;
        this.userSignUpService = userSignUpService;
        this.userInfoUpdateService = userInfoUpdateService;
        this.userDeleteService = userDeleteService;
    }

    @GetMapping("/loginPage")
    public String login(Model model, Principal principal,Authentication authentication){

        return "secu/login";
    }
    @PostMapping("/userSignUpProcess")
    public String userSignUp(Model model,
                             @RequestParam(name = "username") String username,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "realname") String realname,
                             @RequestParam(name = "location") String location)
    {
        LOG.info(username +", "+ password+", "+ realname+", "+ location);
        if(userSignUpService.userSignUp(username, password, realname, location)){
            LOG.info("signsUp success");
            return"/";
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

    @GetMapping("/user/mypage")
    public String myPage(Model model){
        return "secu/mypage";
    }

    @PostMapping("/user/changeInfoProcess")
    public String changeInfoProcess( @RequestParam(name = "username") String username,
                                     @RequestParam(name = "password") String password,
                                     @RequestParam(name = "realname") String realname,
                                     @RequestParam(name = "location") String location)
    {
        LOG.info(username +", "+ password+", "+ realname+", "+ location);
        userInfoUpdateService.userInfoUpdate(username, password, location, realname);
        return "secu/mypage";//나중에 메인페이지로 변경
    }

    @GetMapping("user/quit")
    public String userQuit(Principal principal){
        LOG.info(principal.getName());
        UsersDto usersDto = new UsersDto();
        usersDto.setUsername(principal.getName());
        userDeleteService.userQuit(usersDto);
        return "redirect:/";
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
    public String all(Principal principal) {
        return "secu/all";
    }

    @GetMapping("/loginError")
    public String test() {
        return "secu/loginErrorPage";
    }


}
