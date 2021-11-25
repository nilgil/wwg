package com.project.wwg.admin.service;

import com.project.wwg.admin.dao.SelectMemberListDao;
import com.project.wwg.secu.dto.UsersDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberListTakingService {
    SelectMemberListDao selectMemberListDao;

    MemberListTakingService(SelectMemberListDao selectMemberListDao){
        this.selectMemberListDao = selectMemberListDao;
    }

    public List<UsersDto> getUserList(List<UsersDto> userList){
        userList = selectMemberListDao.getUserList(userList);
        return userList;
    }
}
