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

    public int insertPlan(Plan plan) {
        return plannerDao.insertPlan(plan);
    }

    public Plan getPlanByIdx(int idx) {
        return plannerDao.getPlanByIdx(idx);
    }

    public List<Plan> getPlansByUser(String username) throws ParseException {
        return plannerDao.getPlansByUser(username);
    }

    public String[] getThumbnails(String[] firstSpots) {
        String[] thumbnails = new String[firstSpots.length];
        for (int i = 0; i < firstSpots.length; i++) {
            thumbnails[i] = "https://via.placeholder.com/200";
            if (!firstSpots[i].equals("default"))
                thumbnails[i] = spotsDao.searchSpotOne(firstSpots[i]).getPhoto();
        }
        return thumbnails;
    }

    public int updatePlan(Plan plan) {
        return plannerDao.updatePlan(plan);
    }

    public int deletePlan(int idx){
        return plannerDao.deletePlan(idx);
    }

    public List<Plan> getAllPlansList(PageInfo pageInfo) {
        return plannerDao.getAllPlansList(pageInfo);
    }

    public int getAllPlansCount() {
        return plannerDao.getAllPlansCount();
    }
}
