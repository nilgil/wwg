package com.project.wwg.plan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.service.PlannerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/plan*")
@Slf4j
public class PlannerController {
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
    public String plannerInitForm(Principal principal, Model model) {
        String userName = principal.getName();
        model.addAttribute("userName", userName);

        log.debug("Start Plan Init | Username : {}", userName);
        return "/plan/init-plan";
    }

    /**
     * 2. 유저명과 init 페이지에서 설정한 출발일, 여행기간 플랜생성 페이지로 넘기며 이동
     */
    @PostMapping("/create")
    public String createPlan(Plan plan, Model model) throws JsonProcessingException {
        model.addAttribute("plan", plan);

        log.debug("Create Detail Plan | Departure : {}, Days : {}", plan.getDeparture(), plan.getDays());
        return "plan/create_plan";
    }

    /**
     * 3. 플랜 만들고 저장하면 내 플랜 관리로 이동하는 경로 리턴
     */
    @PostMapping("/success")
    @ResponseBody
    public String createSuccess(Plan plan, Model model) {
        plannerService.insertPlan(plan);

        log.debug("Create Plan Success | Plan : {}", plan);
        return "/plan/my";
    }

    // ----------------------------- 내 플랜 ------------------------------

    /**
     * 내 플랜 관리 이동
     */
    @GetMapping("/my")
    public String myPlan(Principal principal, Model model) throws ParseException {
        String userName = principal.getName();
        List<Plan> plansByUser = plannerService.getPlansByUser(userName);

        String[] firstSpots = new String[plansByUser.size()];
        for (int i = 0; i < plansByUser.size(); i++) {
            JSONParser jsonParser = new JSONParser();
            JSONArray arrayDeep1 = (JSONArray) jsonParser.parse(plansByUser.get(i).getPlans());
            JSONArray arrayDeep2 = (JSONArray) arrayDeep1.get(0);
            firstSpots[i] = String.valueOf(arrayDeep2.get(0));
        }
        String[] thumbnails = plannerService.getThumbnails(firstSpots);

        model.addAttribute("userName", userName);
        model.addAttribute("plans", plansByUser);
        model.addAttribute("thumbnails", thumbnails);
        log.debug("Get My Plans | My Plans : {}, Thumbnails : {}", plansByUser, thumbnails);
        return "/plan/my-plan";
    }

    // --------------------------- 플랜 게시판 ----------------------------

    /**
     * 플랜 게시판 이동
     */
    @GetMapping("/board")
    public String planBoardForm(Model model, int page) {
        int searchCount = plannerService.getAllPlansCount();
        PageInfo pageInfo = new PageInfo(page, searchCount);
        List<Plan> allPlansList = plannerService.getAllPlansList(pageInfo);
        model.addAttribute("plans", allPlansList);

        log.debug("Get All Plans for Board | Plans : {}", allPlansList);
        return "/plan/planBoard";
    }

    // -----------------------------------------------------------


}
