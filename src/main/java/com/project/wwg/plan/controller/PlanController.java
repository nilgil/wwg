package com.project.wwg.plan.controller;

import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/plan")
@Slf4j
public class PlanController {

    private final PlanService planService;
    private final JSONParser jsonParser = new JSONParser();

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    // ----------------------------- 플랜 생성 ------------------------------

    /**
     * 1. 플랜 생성 초기설정 페이지 이동
     */
    @GetMapping("")
    public String planInitForm(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "/plan/init-plan";
    }

    /**
     * 2. 유저명, 출발일, 여행기간 정보를 전달하며 플랜 생성 페이지로 이동
     */
    @PostMapping("/create-form")
    public String planCreateForm(Plan plan, Model model) {
        model.addAttribute("plan", plan);
        model.addAttribute("username", plan.getUsername());
        return "/plan/planner";
    }

    /**
     * 3. 플랜을 저장하고 내 플랜 관리로 이동
     */
    @PostMapping("/save")
    public String savePlan(Plan plan) {
        planService.createPlan(plan);
        return "redirect:/plan/my";
    }

    // ----------------------------- 내 플랜 관리 / 플랜 게시판 ------------------------------

    /**
     * 내 플랜 관리 이동
     */
    @GetMapping("/my")
    public String myPlan(Principal principal, Model model) throws ParseException {
        String username = principal.getName();
        List<Plan> plansByUser = planService.getPlansByUser(username);

        String[] firstSpots = new String[plansByUser.size()];
        for (int i = 0; i < plansByUser.size(); i++) {
            JSONArray arrayDeep1 = (JSONArray) jsonParser.parse(plansByUser.get(i).getPlans());
            JSONArray arrayDeep2 = (JSONArray) arrayDeep1.get(0);

            firstSpots[i] = "default";
            if (!arrayDeep2.isEmpty())
                firstSpots[i] = String.valueOf(arrayDeep2.get(0));
        }

        String[] thumbnails = null;
        if (firstSpots.length != 0) {
            thumbnails = planService.getThumbnails(firstSpots);
        }

        model.addAttribute("thumbnails", thumbnails);
        model.addAttribute("username", username);
        model.addAttribute("plans", plansByUser);

        return "/plan/my-plan";
    }

    /**
     * 플랜의 IDX를 Update Form으로 전달, 수정
     */
    @GetMapping("/{idx}/update-form")
    public String planUpdateForm(@PathVariable int idx, Principal principal, Model model) {
        model.addAttribute("idx", idx);
        model.addAttribute("username", principal.getName());
        return "/plan/planner";
    }

    /**
     * 플랜을 업데이트하고 내 플랜 관리로 이동
     */
    @PutMapping("/{idx}")
    public String updatePlan(Plan plan, @RequestParam("departure") String departure) {
        Date date = Date.valueOf(departure);
        plan.setDeparture(date);
        planService.updatePlan(plan);
        return "redirect:/plan/my";
    }

    /**
     * 플랜의 IDX를 전달하며 상세 페이지 이동
     */
    @GetMapping("/{idx}/detail")
    public String planDetailPage(@PathVariable int idx, Principal principal, Model model) {
        String username = "guest";
        if (principal != null) {
            username = principal.getName();
        }
        planService.increaseHit(idx);
        boolean isAlreadyGood = planService.checkGoodAlready(idx, username);

        model.addAttribute("idx", idx);
        model.addAttribute("username", username);
        model.addAttribute("isAlreadyGood", isAlreadyGood);

        return "/plan/detail-plan";
    }

    /**
     * 플랜 게시판 이동
     */
    @GetMapping("/board")
    public String planBoardForm(Principal principal, Model model) {
        String username = "guest";
        if (principal != null) {
            username = principal.getName();
        }
        List<Plan> bestPlans = planService.getBestPubPlansList();

        String[] titles = new String[bestPlans.size()];
        for (int i = 0; i < bestPlans.size(); i++) {
            if (bestPlans.get(i).getPlans().contains("\"")) {
                titles[i] = bestPlans.get(i).getPlans().split("\"")[1];
            }
        }
        String[] thumbnails = planService.getThumbnails(titles);

        model.addAttribute("bestPlans", bestPlans);
        model.addAttribute("thumbnails", thumbnails);
        model.addAttribute("username", username);

        return "/plan/plan-board";
    }
}