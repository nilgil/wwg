package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.PlannerDao;
import com.project.wwg.plan.dao.SpotsDao;
import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.dto.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlannerServiceImpl {

    private PlannerDao plannerDao;
    private SpotsDao spotsDao;

    @Autowired
    public PlannerServiceImpl(PlannerDao plannerDao, SpotsDao spotsDao) {
        this.plannerDao = plannerDao;
        this.spotsDao = spotsDao;
    }

    public void insertPlan(Plan plan) {
        plannerDao.insertPlan(plan);
    }

    public List<Plan> getPlansByUser(String username) {
        return plannerDao.getPlansByUser(username);
    }

    public List<String> getThumbnails(List<String> firstSpots) {
        List<String> thumbnails = new ArrayList<String>();
        for (String spot : firstSpots) {
            thumbnails.add(spotsDao.searchSpotOne(spot).getPhoto());
        }
        return thumbnails;
    }

    public List<Plan> getAllPlansList(PageInfo pageInfo) {
        return plannerDao.getAllPlansList(pageInfo);
    }

    public int getAllPlansCount() {
        return plannerDao.getAllPlansCount();
    }
}
