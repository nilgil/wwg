package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.Plan;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;


class MybatisPlannerDaoTest {
    private static final Logger logger = LoggerFactory.getLogger(MybatisPlannerDaoTest.class);

    ApplicationContext ac = new GenericXmlApplicationContext("root-context.xml");

    private SqlSession sqlSession;

    @BeforeEach
    void beforeEach() {
        sqlSession = (SqlSession) ac.getBean("sqlsession");
    }

    @Test
    void getPlansByUser() {
        String username = "user1";
        List<Plan> plans = sqlSession.selectList("plan.getPlansByUser", username);
        logger.debug("Username : {}, Plans : {}", username, plans);
    }

//    @Test
//    void insert

}
