package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.PlannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannerServiceImpl {

    private PlannerDao plannerDao;

    @Autowired
    public PlannerServiceImpl(PlannerDao plannerDao) {
        this.plannerDao = plannerDao;
    }
}
