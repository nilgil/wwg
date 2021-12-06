package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MybatisPlanDao implements PlanDao {

    private SqlSession sqlSession;

    @Autowired
    public MybatisPlanDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // ------------------------------------ [C] ------------------------------------
    @Override
    public int createPlan(Plan plan) {
        return sqlSession.insert("plan.insertPlan", plan);
    }

    // ------------------------------------ [R] ------------------------------------
    @Override
    public Plan getPlanByIdx(int idx) {
        return sqlSession.selectOne("plan.getPlanByIdx", idx);
    }

    @Override
    public List<Plan> getPlansByUser(String username) {
        return sqlSession.selectList("plan.getPlansByUser", username);
    }

    @Override
    public List<Plan> getPubPlansList(PageInfo pageInfo) {
        return sqlSession.selectList("plan.getPubPlansList", pageInfo);
    }

    @Override
    public int getPubPlansCount() {
        return sqlSession.selectOne("plan.getPubPlansCount");
    }

    @Override
    public List<Plan> getBest3PubPlansList() {
        return sqlSession.selectList("plan.getBestPubPlansList");
    }

    @Override
    public int checkGoodAlready(Map<String, Object> params) {
        return sqlSession.selectOne("plan.checkGoodAlready", params);
    }

    // ------------------------------------ [U] ------------------------------------
    @Override
    public int updatePlan(Plan plan) {
        return sqlSession.update("plan.updatePlan", plan);
    }

    @Override
    public void pubOn(int idx) {
        sqlSession.update("plan.pubOn", idx);
    }

    @Override
    public void pubOff(int idx) {
        sqlSession.update("plan.pubOff", idx);
    }

    @Override
    public void increaseHit(int idx) {
        sqlSession.update("plan.increaseHit", idx);
    }

    @Override
    public void increaseGood(Map<String, Object> params) {
        sqlSession.insert("plan.increaseGood", params);
    }

    @Override
    public void decreaseGood(Map<String, Object> params) {
        sqlSession.delete("plan.decreaseGood", params);
    }

    // ------------------------------------ [D] ------------------------------------
    @Override
    public int deletePlan(int idx) {
        return sqlSession.delete("plan.deletePlan", idx);
    }

}
