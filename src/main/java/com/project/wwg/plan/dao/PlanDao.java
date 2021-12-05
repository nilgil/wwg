package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;

import java.util.List;
import java.util.Map;

public interface PlanDao {
    // ------------------ [C] ------------------
    int createPlan(Plan plan);

    // ------------------ [R] ------------------
    Plan getPlanByIdx(int idx);

    List<Plan> getPlansByUser(String username);

    List<Plan> getPubPlansList(PageInfo pageInfo);

    int getPubPlansCount();

    List<Plan> getBest3PubPlansList();

    int checkAlreadyGood(Map<String, Object> params);

    // ------------------ [U] ------------------
    int updatePlan(Plan plan);

    void pubOn(int idx);

    void pubOff(int idx);

    void increaseHit(int idx);

    void increaseGood(Map<String, Object> params);

    void decreaseGood(Map<String, Object> params);

    // ------------------ [D] ------------------
    int deletePlan(int idx);

}
