package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;

import java.util.List;
import java.util.Map;

public interface PlanDao {
    // ------------------ [C] ------------------
    int insertPlan(Plan plan);

    // ------------------ [R] ------------------
    Plan getPlanByIdx(int idx);

    List<Plan> getPlansByUser(String username);

    List<Plan> getPubPlansList(PageInfo pageInfo);

    int getPubPlansCount();


    List<Plan> getBestPubPlansList();

    // ------------------ [U] ------------------
    int updatePlan(Plan plan);

    // ------------------ [D] ------------------
    int deletePlan(int idx);


    void pubOn(int idx);

    void pubOff(int idx);

    void increaseHit(int idx);

    int checkGoodAlready(Map<String, Object> params);

    void increaseGood(Map<String, Object> params);

    void decreaseGood(Map<String, Object> params);

    int getGoods(int idx);
}
