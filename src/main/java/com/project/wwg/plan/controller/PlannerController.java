package com.project.wwg.plan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.exceptions.NotLogInUserException;
import com.project.wwg.plan.service.PlannerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/plan*")
@Slf4j
public class PlannerController {
    private final JSONParser jsonParser = new JSONParser();
    private final Gson gson = new Gson();
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
            return "redirect:/loginPage";
        }
        String username = principal.getName();
        model.addAttribute("username", username);

        log.debug("Start Plan Init | Username : {}", username);
        return "/plan/init-plan";
    }

    /**
     * 2. 유저명, 출발일, 여행기간 정보를 가지고 플랜 생성 페이지로 이동
     */
    @PostMapping("/create")
    public String createPlan(Plan plan, Model model) throws JsonProcessingException {
        model.addAttribute("plan", plan);

        log.debug("Create Detail Plan | Username : {}, Departure : {}, Days : {}", plan.getUsername(), plan.getDeparture(), plan.getDays());
        return "/plan/planner";
    }

    /**
     * 3. 플랜을 저장하고 내 플랜 관리로 이동
     */
    @PostMapping("/save")
    @ResponseBody
    public String createSuccess(Plan plan) {
        try {
            plannerService.insertPlan(plan);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("Create Plan Success | Plan : {}", plan);
        return "/plan/my";
    }

    // ----------------------------- 내 플랜 ------------------------------

    /**
     * 내 플랜 관리 이동
     */
    @GetMapping("/my")
    public String myPlan(Principal principal, Model model) throws ParseException, NotLogInUserException {
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


    /**
     * 플랜의 IDX를 가지고 Planner로 이동
     */
    @GetMapping("/update-form/{idx}")
    public String moveToUpdateForm(@PathVariable int idx, Principal principal, Model model) {
        model.addAttribute("idx", idx);
        model.addAttribute("username", principal.getName());
        return "/plan/planner";
    }

    /**
     * IDX로 플랜 정보 가져오기
     */
    @GetMapping(value = "/{idx}", produces = "application/json; charset=utf8")
    @ResponseBody
    public String getPlanByIdx(@PathVariable int idx) {
        Plan plan = plannerService.getPlanByIdx(idx);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", plan.getUsername());
        jsonObject.addProperty("idx", plan.getIdx());
        jsonObject.addProperty("title", plan.getTitle());
        jsonObject.addProperty("departure", plan.getDeparture().toString());
        jsonObject.addProperty("days", plan.getDays());
        jsonObject.addProperty("plans", plan.getPlans());
        jsonObject.addProperty("hit", plan.getHit());
        jsonObject.addProperty("good", plan.getGood());
        jsonObject.addProperty("pub", plan.getPub());

        log.debug("Get User's Plan | Username : {}, Plan IDX : {}", plan.getUsername(), plan.getIdx());
        return jsonObject.toString();
    }

    @PutMapping(value = "/update", produces = "application/x-www-form-urlencoded;charset=UTF-8")
    @ResponseBody
    public void updatePlan(String username, String departure, int days, String title, String plans, int idx) {
        Date date = Date.valueOf(departure);
        Plan plan = new Plan();
        plan.setUsername(username);
        plan.setDeparture(date);
        plan.setDays(days);
        plan.setTitle(title);
        plan.setPlans(plans);
        plan.setIdx(idx);
        plannerService.updatePlan(plan);
    }

    @GetMapping("/view/{idx}")
    public String planViewPage(@PathVariable int idx, Principal principal, Model model) throws ParseException {
        String username = "guest";
        if (principal != null) {
            username = principal.getName();
        }
        log.debug("[planViewPage] View Plan Detail | Param : idx={}", idx);

        boolean isAlreadyGood = plannerService.checkGoodAlready(idx, username);

        plannerService.increaseHit(idx);
        plannerService.checkGoodAlready(idx, username);
        model.addAttribute("idx", idx);
        model.addAttribute("username", username);
        model.addAttribute("isAlreadyGood", isAlreadyGood);

        log.debug("[planViewPage] Result : IDX={}, Hit + 1", idx);
        return "/plan/view-plan";
    }

    @PutMapping("/good")
    @ResponseBody
    public String goodToggle(int idx, String username, String beforeUrl, HttpServletRequest request) {
        log.debug("[goodToggle] Good Toggle | Param : IDX={}, Username={}", idx, username);
        log.info(beforeUrl);

        if (username.equals("guest")) {
            HttpSession session = request.getSession();
            session.setAttribute("beforeUrl",beforeUrl);
            return "/loginPage";
        }

        String result = "";
        try {
            boolean isAlreadyGood = plannerService.checkGoodAlready(idx, username);
            if (isAlreadyGood) {
                plannerService.decreaseGood(idx, username);
                result = "decrease";
            } else {
                plannerService.increaseGood(idx, username);
                result = "increase";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * idx로 플랜 삭제
     */
    @DeleteMapping("/delete")
    @ResponseBody
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
    public String planBoardForm(Model model) {
        List<Plan> bestPlans = plannerService.getBestPubPlansList();

        String[] titles = new String[bestPlans.size()];
        for (int i = 0; i < bestPlans.size(); i++) {
            if (bestPlans.get(i).getPlans().contains("\"")) {
                titles[i] = bestPlans.get(i).getPlans().split("\"")[1];
            }
        }
        String[] thumbnails = plannerService.getThumbnails(titles);

        model.addAttribute("bestPlans", bestPlans);
        model.addAttribute("thumbnails", thumbnails);

        log.debug("Move To Plan Board | Best Plans : {}", bestPlans);
        return "/plan/plan-board";
    }

    @GetMapping(value = "/pub", produces = "application/json; charset=utf8")
    @ResponseBody
    public String getPubPlans(int page) {
        log.debug("Get All Pub Plans | Parameter : page={}", page);

        int pubPlansCount = plannerService.getPubPlansCount();
        PageInfo pageInfo = new PageInfo(10, page, pubPlansCount);
        List<Plan> pubPlansList = plannerService.getPubPlansList(pageInfo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("plans", pubPlansList);
        jsonObject.put("pageInfo", pageInfo);

        String result = gson.toJson(jsonObject);

        log.debug("Return : {}", result);
        return gson.toJson(result);
    }

    @PutMapping("/pub-toggle")
    @ResponseBody
    public void togglePub(int idx, int pub) {
        log.debug("Toggle Public/Private Plan | Parameter : idx={}, pub={}", idx, pub);
        plannerService.togglePub(idx, pub);
    }

    // -----------------------------------------------------------


}
