package com.project.wwg.secu.service;

import com.project.wwg.secu.controller.SecuController;
import com.project.wwg.secu.dao.UserDeleteDao;
import com.project.wwg.secu.dto.UsersDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteService {
    private static final Log LOG = LogFactory.getLog(UserDeleteService.class);

    UserDeleteDao userDeleteDao;

    UserDeleteService(UserDeleteDao userDeleteDao){
        this.userDeleteDao = userDeleteDao;
    }

    public void userQuit(UsersDto usersDto){
        LOG.info(usersDto.getUsername());
        userDeleteDao.userQuit(usersDto);
    }
}
