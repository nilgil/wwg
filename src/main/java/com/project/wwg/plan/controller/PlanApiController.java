package com.project.wwg.plan.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/plan")
@Slf4j
public class PlanApiController {

    private final PlanService planService;
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public PlanApiController(PlanService planService) {
        this.planService = planService;
    }

    /**
     * IDX로 플랜 상세정보 가져오기
     */
    @GetMapping("/{idx}")
    public String getPlanByIdx(@PathVariable int idx) {
        Plan plan = planService.getPlanByIdx(idx);
        return gson.toJson(plan);
    }

    /**
     * IDX로 플랜 삭제
     */
    @DeleteMapping("/{idx}")
    public void deletePlan(@PathVariable int idx) {
        planService.deletePlan(idx);
    }

    /**
     * 공개 처리된 플랜 리스트, 페이지 정보 가져오기
     */
    @GetMapping("/list/pub/{page}")
    public String getPubPlans(@PathVariable int page) {
        int pubPlansCount = planService.getPubPlansCount();
        PageInfo pageInfo = new PageInfo(10, page, pubPlansCount);
        List<Plan> pubPlans = planService.getPubPlansList(pageInfo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("plans", pubPlans);
        jsonObject.put("pageInfo", pageInfo);

        String result = gson.toJson(jsonObject);
        return gson.toJson(result);
    }

    /**
     * 플랜의 공개 여부 검사 후 공개/비공개 처리
     */
    @PutMapping("/{idx}/pub")
    public void pubToggle(@PathVariable int idx, int pub) {
        planService.pubToggle(idx, pub);
    }

    /**
     * 접속 유저의 좋아요 여부 확인 후 좋아요 증,감 처리
     */
    @PutMapping("/{idx}/good")
    public String goodToggle(@PathVariable int idx, @RequestBody String username, String beforeUrl, HttpSession session) {
        // 비로그인시
        if (username.equals("guest")) {
            session.setAttribute("beforeUrl", beforeUrl);
            return username;
        }
        // 이전에 좋아요를 눌렀었는지 여부
//        boolean isAlreadyGood = planService.checkGoodAlready(idx, username);
//
//        if (isAlreadyGood) {
//            planService.decreaseGood(idx, username);
//        } else {
//            planService.increaseGood(idx, username);
//        }
//        return String.valueOf(isAlreadyGood);
        return "true";
    }

}
