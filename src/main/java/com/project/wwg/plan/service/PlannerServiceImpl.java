package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.PlannerDao;
import com.project.wwg.plan.dto.Page;
import com.project.wwg.plan.dto.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlannerServiceImpl {

    private PlannerDao plannerDao;

    @Autowired
    public PlannerServiceImpl(PlannerDao plannerDao) {
        this.plannerDao = plannerDao;
    }

    public void insertPlan(Plan plan) {
        plannerDao.insertPlan(plan);
    }

    public List<Plan> getPlansByUser(String username) {
        return plannerDao.getPlansByUser(username);
    }

    public List<Plan> getAllPlansList(Page page) {
        return plannerDao.getAllPlansList(page);
    }

    public int getAllPlansCount() {
        return plannerDao.getAllPlansCount();
    }
}
