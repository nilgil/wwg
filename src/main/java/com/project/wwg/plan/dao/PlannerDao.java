package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.Page;
import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.dto.Spot;

import java.util.List;

public interface PlannerDao {
    void insertPlan(Plan plan);
    List<Plan> getPlansByUser(String username);

    List<Plan> getAllPlansList(Page page);

    int getAllPlansCount();
}
