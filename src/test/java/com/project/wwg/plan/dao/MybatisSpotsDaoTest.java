package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.Spot;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MybatisSpotsDaoTest {

    ApplicationContext ac = new GenericXmlApplicationContext("root-context.xml");

    private SqlSession sqlSession;

    @BeforeEach
    void beforeEach() {
        sqlSession = (SqlSession) ac.getBean("sqlsession");
    }

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("bean = " + bean);
        }
    }

    @Test
    public void insertSpot() {
        Spot spot = new Spot("title", "info", 123, 123, "address", "photo", "phone");
        int result = sqlSession.insert("spot.insertSpot", spot);
        System.out.println("result = " + result);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void insertSpots() {
        List<Spot> spots = new ArrayList<Spot>();
        Spot spot1 = new Spot("title4", "info", 123, 123, "address", "photo", "phone");
        Spot spot2 = new Spot("title5", "info", 123, 123, "address", "photo", "phone");
        Spot spot3 = new Spot("title6", "info", 123, 123, "address", "photo", "phone");
        spots.add(spot1);
        spots.add(spot2);
        spots.add(spot3);
        int result = sqlSession.insert("spot.insertSpots", spots);
        System.out.println("result = " + result);
        assertThat(result).isEqualTo(3);
    }
}
