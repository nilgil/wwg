package com.project.wwg.plan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.wwg.plan.dto.Page;
import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.exceptions.NotAvailableDataException;
import com.project.wwg.plan.service.PlannerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/planner*")
public class PlannerController {
    private static final Logger logger = LoggerFactory.getLogger(PlannerController.class);

    private PlannerServiceImpl plannerService;

    @Autowired
    public PlannerController(PlannerServiceImpl plannerService) {
        this.plannerService = plannerService;
    }

    // ----------------------------- 플랜 생성 ------------------------------

    /**
     * 1. 플랜 생성 초기설정 페이지 이동
     */
    @GetMapping("")
    public String plannerInitForm(Principal principal, Model model) throws NotAvailableDataException {
        String username = principal.getName();
        model.addAttribute("username", username);

        logger.info("Start Plan Init | Username : {}", username);
        return "/plan/planInitForm";
    }

    /**
     * 2. 유저명과 init 페이지에서 설정한 출발일, 여행기간 플랜생성 페이지로 넘기며 이동
     */
    @PostMapping("/create_plan")
    public String createPlan(Plan plan, Model model) throws JsonProcessingException {
        model.addAttribute("plan", plan);

        logger.debug("Create Detail Plan | Departure : {}, Days : {}", plan.getDeparture(), plan.getDays());
        return "/plan/planCreateForm";
    }

    /**
     * 3. 플랜 만들고 저장하면 내 플랜 관리로 이동하는 경로 리턴
     */
    @PostMapping("/create_success")
    @ResponseBody
    public String createSuccess(Plan plan, Model model) {
        plannerService.insertPlan(plan);

        logger.debug("Create Plan Success | Plan : {}", plan);
        return "/planner/my";
    }

    /**
     * 4. 내 플랜 관리 이동
     */
    @GetMapping("/my")
    public String myPlan(Principal principal, Model model) {
        String username = principal.getName();
        List<Plan> plansByUser = plannerService.getPlansByUser(username);
        model.addAttribute("plans", plansByUser);

        logger.debug("Get Plans By User | PlansCount : {}, Plans : {}",plansByUser.size(),plansByUser);
        return "/plan/myPlan";
    }

    /**
     * 플랜 게시판 이동
     */
    @GetMapping("/board/page/{pageNum}")
    public String planBoardForm(Model model, @PathVariable int pageNum) {
        int searchCount = plannerService.getAllPlansCount();
        Page page = new Page(pageNum, searchCount);
        List<Plan> allPlansList = plannerService.getAllPlansList(page);
        model.addAttribute("plans", allPlansList);

        logger.debug("Get All Plans for Board | Plans : {}", allPlansList);
        return "/plan/planBoard";
    }

    // -----------------------------------------------------------


}
