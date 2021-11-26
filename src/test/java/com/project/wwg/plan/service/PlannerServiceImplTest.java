package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.MybatisPlannerDao;
import com.project.wwg.plan.dao.MybatisSpotsDao;
import com.project.wwg.plan.dao.PlannerDao;
import com.project.wwg.plan.dao.SpotsDao;
import com.project.wwg.plan.dto.Plan;
import com.project.wwg.plan.dto.Spot;
import org.assertj.core.api.Assertions;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
public class PlannerServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(PlannerServiceImplTest.class);
    ApplicationContext ac = new GenericXmlApplicationContext("root-context.xml");

    private PlannerDao plannerDao;

    @BeforeEach
    void beforeEach() {
        plannerDao = ac.getBean(MybatisPlannerDao.class);
    }

    @Test
    @DisplayName("존재하는 유저명으로 플랜들 검색")
    void getPlansByUser() {
        String username = "user1";
        List<Plan> plans = plannerDao.getPlansByUser(username);
        logger.debug("Username : {}, Plans : {}", username, plans);
        Assertions.assertThat(plans).isNotEmpty();
    }

    @Test
    @DisplayName("존재하지 않는 유저명으로 플랜들 검색")
    void getPlansByUnknown() {
        String username = "unknown";
        List<Plan> plans = plannerDao.getPlansByUser(username);
        logger.debug("Username : {}, Plans : {}", username, plans);
        Assertions.assertThat(plans).isEmpty();
    }

}
