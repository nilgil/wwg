package com.project.wwg.plan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/planner*")
public class PlannerController {
    // ----------------------------- Form 이동 ------------------------------
    @GetMapping("")
    public String plannerForm() {

        return "/plan/plannerForm";
    }
}
