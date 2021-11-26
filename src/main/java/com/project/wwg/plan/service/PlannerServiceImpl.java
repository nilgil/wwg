package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.PlannerDao;
import com.project.wwg.plan.dao.SpotsDao;
import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.dto.Spot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

    public List<Plan> getPlansByUser(String userName) throws ParseException {
        return plannerDao.getPlansByUser(userName);
    }

    public String[] getThumbnails(String[] firstSpots) {
        String[] thumbnails = new String[firstSpots.length];
        for (int i = 0; i < firstSpots.length; i++) {
            thumbnails[i] = spotsDao.searchSpotOne(firstSpots[i]).getPhoto();
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
