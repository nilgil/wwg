package com.project.wwg.secu.service;

import com.project.wwg.secu.dao.TestDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private static final Log LOG = LogFactory.getLog(TestService.class);

    TestDao testDao;
    @Autowired
    TestService(TestDao testDao){
        this.testDao = testDao;
    }

    public String getUsers(String user){
        LOG.info("service!!");
        return testDao.getUsers(user);
    }
}
