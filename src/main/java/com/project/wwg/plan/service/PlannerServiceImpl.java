package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.PlannerDao;
import com.project.wwg.plan.dao.SpotsDao;
import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.dto.PlanReply;
import com.project.wwg.plan.dto.Spot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            if (!firstSpots[i].equals("default")) {
                thumbnails[i] = spotsDao.searchSpotOne(firstSpots[i]).getPhoto();
            }
        }
        return thumbnails;
    }

    public List<PlanReply> getPlanReplys(int plan_no) {
        return plannerDao.getPlanReplys(plan_no);
    }

    public int updatePlan(Plan plan) {
        return plannerDao.updatePlan(plan);
    }

    public int deletePlan(int idx) {
        return plannerDao.deletePlan(idx);
    }

    public List<Plan> getPubPlansList(PageInfo pageInfo) {
        return plannerDao.getPubPlansList(pageInfo);
    }

    public int getPubPlansCount() {
        return plannerDao.getPubPlansCount();
    }

    public List<Plan> getBestPubPlansList() {
        return plannerDao.getBestPubPlansList();
    }

    public void togglePub(int idx, int pub) {
        if (pub == 0) {
            plannerDao.pubOn(idx);
        }
        if (pub == 1) {
            plannerDao.pubOff(idx);
        }

    }

    public void increaseHit(int idx) {
        plannerDao.increaseHit(idx);
    }

    public boolean checkGoodAlready(int idx, String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("idx", idx);
        params.put("username", username);

        return plannerDao.checkGoodAlready(params) == 1;
    }

    public void increaseGood(int idx, String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("idx", idx);
        params.put("username", username);

        plannerDao.increaseGood(params);
    }

    public void decreaseGood(int idx, String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("idx", idx);
        params.put("username", username);

        plannerDao.decreaseGood(params);
    }

    public int getGoods(int idx) {
        return plannerDao.getGoods(idx);
    }
}
