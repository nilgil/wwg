package com.project.wwg.secu.service;

import com.project.wwg.secu.controller.SecuController;
import com.project.wwg.secu.dao.UserInfoDao;
import com.project.wwg.secu.dto.UsersDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    private static final Log LOG = LogFactory.getLog(UserInfoService.class);

    UserInfoDao userInfoDao;

    public UserInfoService(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    public UsersDto getUserInfo(UsersDto usersDto){
        LOG.info("서비스진입");
        String username = usersDto.getUsername();
        LOG.info(username);
        usersDto = userInfoDao.getUserInfo(usersDto,username);
        LOG.info(usersDto.getLocation());
        return usersDto;
    }
}
