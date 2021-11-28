package com.project.wwg.plan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.exceptions.NotLogInUserException;
import com.project.wwg.plan.service.PlannerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/plan*")
@Slf4j
public class PlannerController {
    private final JSONParser jsonParser = new JSONParser();
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
    public String plannerInitForm(Principal principal, Model model) throws NotLogInUserException {
        if (principal == null) {
            throw new NotLogInUserException("로그인 하지 않은 상태입니다.");
        }
        String username = principal.getName();
        model.addAttribute("username", username);

        log.debug("Start Plan Init | Username : {}", username);
        return "/plan/init-plan";
    }

    /**
     * 2. 유저명과 init 페이지에서 설정한 출발일, 여행기간 플랜생성 페이지로 넘기며 이동
     */
    @PostMapping("/create")
    public String createPlan(Plan plan, Model model) throws JsonProcessingException {
        model.addAttribute("plan", plan);

        log.debug("Create Detail Plan | Username : {}, Departure : {}, Days : {}", plan.getUsername(), plan.getDeparture(), plan.getDays());
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
    public String myPlan(Principal principal, Model model) throws ParseException, NotLogInUserException {
        if (principal == null) {
            throw new NotLogInUserException("로그인 하지 않은 상태입니다.");
        }
        String username = principal.getName();
        List<Plan> plansByUser = plannerService.getPlansByUser(username);

        String[] firstSpots = new String[plansByUser.size()];
        for (int i = 0; i < plansByUser.size(); i++) {
            JSONArray arrayDeep1 = (JSONArray) jsonParser.parse(plansByUser.get(i).getPlans());
            JSONArray arrayDeep2 = (JSONArray) arrayDeep1.get(0);

            firstSpots[i] = "default";
            if (!arrayDeep1.isEmpty() && !arrayDeep2.isEmpty())
                firstSpots[i] = String.valueOf(arrayDeep2.get(0));
        }

        String[] thumbnails = null;
        if (firstSpots.length != 0) {
            thumbnails = plannerService.getThumbnails(firstSpots);
        }
        model.addAttribute("thumbnails", thumbnails);
        model.addAttribute("username", username);
        model.addAttribute("plans", plansByUser);

        log.debug("Get User's Plans | Username : {}, Plans Count : {}", username, plansByUser.size());
        return "/plan/my-plan";
    }

    @GetMapping("/update-form/{idx}")
    public String moveToUpdateForm(@PathVariable int idx, Model model) {
        model.addAttribute("idx", idx);
        return "/plan/update-plan";
    }

    /**
     * IDX로 플랜 정보 가져오기
     */
    @GetMapping(value = "/{idx}", produces = "application/json; charset=utf8")
    @ResponseBody
    public String getPlanByIdx(@PathVariable int idx) throws ParseException {
        Plan plan = plannerService.getPlanByIdx(idx);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", plan.getUsername());
        jsonObject.addProperty("idx", plan.getIdx());
        jsonObject.addProperty("title", plan.getTitle());
        jsonObject.addProperty("departure", plan.getDeparture().toString());
        jsonObject.addProperty("days", plan.getDays());
        jsonObject.addProperty("plans", plan.getPlans());

        log.debug("Update User's Plan | Username : {}, Plan IDX : {}", plan.getUsername(), plan.getIdx());
        return jsonObject.toString();
    }

    @PutMapping("/update")
    public String updatePlan(Plan plan) {
        plannerService.updatePlan(plan);
        return "/plan/my-plan";
    }


    /**
     * idx로 플랜 삭제
     */
    @DeleteMapping("/delete")
    public void deletePlan(Principal principal, int idx) throws NotLogInUserException {
        if (principal == null) {
            throw new NotLogInUserException("로그인 하지 않은 상태입니다.");
        }
        String username = principal.getName();
        log.debug("Delete User's Plan | Username : {}, Plan IDX : {}", username, idx);
        plannerService.deletePlan(idx);
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
