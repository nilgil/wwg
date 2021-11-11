package com.project.wwg.plan.controller;

import com.project.wwg.plan.service.PlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/planner/*")
public class PlannerController {

    private PlannerService plannerService;

    @Autowired
    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    @RequestMapping("plans")
    public String showMyPlans(Model model, HttpServletRequest request) {

        String memberid = "giri";



        return "/plan/my_plans";
    }

    @RequestMapping("create")
    public String planCreateForm(){

        return "/plan/plan_create_form";
    }


}
