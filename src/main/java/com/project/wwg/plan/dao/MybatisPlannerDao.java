package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Plan;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MybatisPlannerDao implements PlannerDao {

    private SqlSession sqlSession;

    @Autowired
    public MybatisPlannerDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    // ------------------------------------ [C] ------------------------------------
    @Override
    public int insertPlan(Plan plan) {
        return sqlSession.insert("plan.insertPlan", plan);
    }

    // ------------------------------------ [R] ------------------------------------
    @Override
    public List<Plan> getPlansByUser(String username) {
        return sqlSession.selectList("plan.getPlansByUser", username);
    }

    @Override
    public List<Plan> getAllPlansList(PageInfo pageInfo) {
        return sqlSession.selectList("plan.getAllPlansList",pageInfo);
    }

    @Override
    public int getAllPlansCount() {
        return sqlSession.selectOne("plan.getAllPlansCount");
    }

    // ------------------------------------ [U] ------------------------------------


    // ------------------------------------ [D] ------------------------------------
}
