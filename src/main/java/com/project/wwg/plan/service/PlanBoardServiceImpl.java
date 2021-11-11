package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.PlanBoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanBoardServiceImpl implements PlanBoardService {
    private PlanBoardDao planBoardDao;

    @Autowired
    public PlanBoardServiceImpl(PlanBoardDao planBoardDao) {
        this.planBoardDao = planBoardDao;
    }
}
