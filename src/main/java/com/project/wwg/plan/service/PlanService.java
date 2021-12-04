package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.PlanDao;
import com.project.wwg.plan.dao.SpotDao;
import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlanService {

    private final PlanDao planDao;
    private final SpotDao spotDao;

    public PlanService(PlanDao planDao, SpotDao spotDao) {
        this.planDao = planDao;
        this.spotDao = spotDao;
    }

    public int insertPlan(Plan plan) {
        return planDao.insertPlan(plan);
    }

    public Plan getPlanByIdx(int idx) {
        return planDao.getPlanByIdx(idx);
    }

    public List<Plan> getPlansByUser(String username) throws ParseException {
        return planDao.getPlansByUser(username);
    }

    public String[] getThumbnails(String[] firstSpots) {
        String[] thumbnails = new String[firstSpots.length];
        for (int i = 0; i < firstSpots.length; i++) {
            thumbnails[i] = "https://via.placeholder.com/200";
            if (!firstSpots[i].equals("default")) {
                thumbnails[i] = spotDao.searchSpotOne(firstSpots[i]).getPhoto();
            }
        }
        return thumbnails;
    }

    public int updatePlan(Plan plan) {
        return planDao.updatePlan(plan);
    }

    public int deletePlan(int idx) {
        return planDao.deletePlan(idx);
    }

    public List<Plan> getPubPlansList(PageInfo pageInfo) {
        return planDao.getPubPlansList(pageInfo);
    }

    public int getPubPlansCount() {
        return planDao.getPubPlansCount();
    }

    public List<Plan> getBestPubPlansList() {
        return planDao.getBestPubPlansList();
    }

    public void togglePub(int idx, int pub) {
        if (pub == 0) {
            planDao.pubOn(idx);
        }
        if (pub == 1) {
            planDao.pubOff(idx);
        }

    }

    public void increaseHit(int idx) {
        planDao.increaseHit(idx);
    }

    public boolean checkGoodAlready(int idx, String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("idx", idx);
        params.put("username", username);

        return planDao.checkGoodAlready(params) == 1;
    }

    public void increaseGood(int idx, String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("idx", idx);
        params.put("username", username);

        planDao.increaseGood(params);
    }

    public void decreaseGood(int idx, String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("idx", idx);
        params.put("username", username);

        planDao.decreaseGood(params);
    }

}
