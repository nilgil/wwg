package com.project.wwg.plan.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisPlannerDao implements PlannerDao {

    private SqlSession sqlSession;

    @Autowired
    public MybatisPlannerDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


}
