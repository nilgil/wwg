package com.project.wwg.secu.service;

import com.project.wwg.secu.dao.UserInfoUpdateDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserInfoUpdateService {
    private static final Log LOG = LogFactory.getLog(UserSignUpService.class);

    UserInfoUpdateDao userInfoUpdateDao;

    UserInfoUpdateService(UserInfoUpdateDao userInfoUpdateDao){
        this.userInfoUpdateDao = userInfoUpdateDao;
    }

    public void userInfoUpdate(String username, String password, String realname, String location){
        userInfoUpdateDao.userInfoUpdate(username, password, realname, location);
    }
}
