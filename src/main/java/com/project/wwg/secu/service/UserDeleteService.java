package com.project.wwg.secu.service;

import com.project.wwg.secu.dao.UserDeleteDao;
import com.project.wwg.secu.dto.UsersDto;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteService {

    UserDeleteDao userDeleteDao;

    UserDeleteService(UserDeleteDao userDeleteDao){
        this.userDeleteDao = userDeleteDao;
    }

    public void userQuit(UsersDto usersDto){
        userDeleteDao.userQuit(usersDto);
    }
}
