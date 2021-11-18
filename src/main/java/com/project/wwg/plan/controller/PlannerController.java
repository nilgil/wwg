package com.project.wwg.plan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.wwg.plan.dto.Plan;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@Controller
@RequestMapping("/planner*")
public class PlannerController {
    // ----------------------------- Form 이동 ------------------------------
    @GetMapping("")
    public String plannerInitForm() {
        return "/plan/planInitForm";
    }

    @GetMapping("/my")
    public String myPlan() {
        return "/plan/myPlan";
    }

    @GetMapping("/board")
    public String planBoardForm() {
        return "/plan/planBoard";
    }

    // -----------------------------------------------------------
    @PostMapping("/create_plan")
    public String createPlan(Date departure, int days, Model model) throws JsonProcessingException {
        model.addAttribute("departure", departure);
        model.addAttribute("days", days);
        return "/plan/planCreateForm";
    }

    @PostMapping("/create_success")
    public String createSuccess(Plan plan, Model model) {
        model.addAttribute("plan", plan);
        return "/plan/planList";
    }
}
