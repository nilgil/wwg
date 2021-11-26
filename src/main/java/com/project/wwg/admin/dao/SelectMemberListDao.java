package com.project.wwg.admin.dao;

import com.project.wwg.secu.dto.UsersDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SelectMemberListDao {
    protected static final Log LOG = LogFactory.getLog(SelectMemberListDao.class);
    @Autowired
    protected SqlSession sqlSession;
    protected final String NAMESPACE = "AdminMapper.";

    public List<UsersDto> getUserList(List<UsersDto> userList){
        LOG.info("dao getlist 접근");
        userList=sqlSession.selectList(NAMESPACE+"getUserList");
        return userList;
    }
}
