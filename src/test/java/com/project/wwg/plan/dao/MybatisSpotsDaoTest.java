package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.Spot;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(locations = "../../../../../../resources/")
class MybatisSpotsDaoTest {

    private SqlSession sqlSession;
    ApplicationContext ac = new GenericXmlApplicationContext("root-context.xml");

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("bean = " + bean);
        }
    }

    @BeforeEach
    void beforeEach() {
        sqlSession = (SqlSession) ac.getBean("session");
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
        int result = sqlSession.insert("spot.insertSpots",spots);
        System.out.println("result = " + result);
        assertThat(result).isEqualTo(3);
    }
}
