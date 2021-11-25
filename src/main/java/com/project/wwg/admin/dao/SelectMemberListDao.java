package com.project.wwg.admin.dao;

import com.project.wwg.secu.dto.UsersDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class SelectMemberListDao extends AbstractAdminDao{

    public List<UsersDto> getUserList(List<UsersDto> userList){
        userList = sqlSession.selectList(NAMESPACE+"getUserList");
        return userList;
    }
}
