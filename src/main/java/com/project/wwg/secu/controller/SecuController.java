package com.project.wwg.secu.controller;

import com.project.wwg.secu.dto.UsersDto;
import com.project.wwg.secu.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class SecuController {
    private static final Log LOG = LogFactory.getLog(SecuController.class);

    TestService testService;
    UserSignUpService userSignUpService;
    UserInfoUpdateService userInfoUpdateService;
    UserDeleteService userDeleteService;
    UserInfoService userInfoService;

    SecuController(TestService testService, UserSignUpService userSignUpService,
                   UserInfoUpdateService userInfoUpdateService,
                   UserDeleteService userDeleteService,UserInfoService userInfoService){
        this.testService = testService;
        this.userSignUpService = userSignUpService;
        this.userInfoUpdateService = userInfoUpdateService;
        this.userDeleteService = userDeleteService;
        this.userInfoService = userInfoService;
    }

    @GetMapping("/loginPage")
    public String login(Model model, Principal principal,Authentication authentication){
        model.addAttribute("username","guest");
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
            return"redirect:/";
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
    public String myPage(Model model, Principal principal){
        UsersDto usersDto = new UsersDto();
        LOG.info(principal.getName());
        usersDto.setUsername(principal.getName());
        LOG.info(usersDto.getUsername());
        usersDto=userInfoService.getUserInfo(usersDto);
        model.addAttribute("userInfo",usersDto);
        model.addAttribute("username",principal.getName());
        return "secu/mypage";
    }

    @PutMapping("/user/changeInfoProcess")
    public String changeInfoProcess( @RequestParam(name = "username") String username,
                                     @RequestParam(name = "password") String password,
                                     @RequestParam(name = "realname") String realname,
                                     @RequestParam(name = "location") String location)
    {
        LOG.info(username +", "+ password+", "+ realname+", "+ location);
        userInfoUpdateService.userInfoUpdate(username, password, location, realname);
        return "redirect:/";//나중에 메인페이지로 변경
    }

    @GetMapping("/user/quit")
    public String userQuit(Principal principal,Authentication authentication){
        LOG.info(principal.getName());
        UsersDto usersDto = new UsersDto();
        usersDto.setUsername(principal.getName());
        userDeleteService.userQuit(usersDto);
        principal=null;
        authentication=null;
        LOG.info(principal);
        return "redirect:/logout";
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

    @GetMapping("/loginSuccess")
    public String loginSuccess(HttpSession session) {
        String beforeUrl = (String) session.getAttribute("beforeUrl");
        if (beforeUrl != null) {
            return "redirect:" + beforeUrl;
        }
        return "redirect:/";
    }
}
