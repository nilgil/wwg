package com.project.wwg.plan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.service.PlannerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/planner*")
public class PlannerController {
    private static final Logger logger = LoggerFactory.getLogger(PlannerController.class);

    private PlannerServiceImpl plannerService;

    @Autowired
    public PlannerController(PlannerServiceImpl plannerService) {
        this.plannerService = plannerService;
    }

    // ----------------------------- Form 이동 ------------------------------

    /**
     * 플랜 생성 초기설정 페이지 이동
     */
    @GetMapping("")
    public String plannerInitForm(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("username", username);
        logger.info("username : " + username);
        return "/plan/planInitForm";
    }

    /**
     * 유저명과 init 페이지에서 설정한 출발일, 여행기간 플랜생성 페이지로 넘기며 이동
     */
    @PostMapping("/create_plan")
    public String createPlan(Plan plan, Model model) throws JsonProcessingException {
        model.addAttribute("plan", plan);
        return "/plan/planCreateForm";
    }

    /**
     * 플랜 만들고 저장하면 내 플랜으로 이동
     */
    @PostMapping("/create_success")
    @ResponseBody
    public String createSuccess(Plan plan, Model model) {
        plannerService.insertPlan(plan);
        return "/planner/my";
    }

    /**
     * 내 플랜 관리 이동
     */
    @GetMapping("/my")
    public String myPlan() {
        return "/plan/myPlan";
    }

    /**
     * 플랜 게시판 이동
     */
    @GetMapping("/board")
    public String planBoardForm() {
        return "/plan/planBoard";
    }

    // -----------------------------------------------------------


}
