package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;

import java.util.List;

public interface PlannerDao {
    // ------------------ [C] ------------------
    int insertPlan(Plan plan);

    // ------------------ [R] ------------------
    List<Plan> getPlansByUser(String username);

    List<Plan> getAllPlansList(PageInfo pageInfo);

    int getAllPlansCount();

    // ------------------ [U] ------------------

    // ------------------ [D] ------------------
}
