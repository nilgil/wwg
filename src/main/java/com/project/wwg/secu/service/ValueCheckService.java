package com.project.wwg.secu.service;

import com.google.gson.Gson;
import com.project.wwg.secu.dao.ValueCheckDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class ValueCheckService {
    private static final Log LOG = LogFactory.getLog(ValueCheckDao.class);
    ValueCheckDao valueCheckDao;
    ValueCheckService(ValueCheckDao valueCheckDao){
        this.valueCheckDao = valueCheckDao;
    }

    public String isUserHere(String username){
        boolean userEmpty = valueCheckDao.isUserHere(username);
        if(userEmpty) {
            return "You can use this ID";
        }else {
            return "This is impossible to use as your ID";
        }
    }


}
