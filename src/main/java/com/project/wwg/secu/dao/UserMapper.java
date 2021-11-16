package com.project.wwg.secu.dao;

import com.project.wwg.secu.dto.TestDto;
import com.project.wwg.secu.dto.UserDetailsVO;
import com.project.wwg.secu.dto.UserInfoVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    private static final Log LOG = LogFactory.getLog(UserMapper.class);
    private SqlSession sqlSession;

    @Autowired
    UserMapper(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }
    private final String NAMESPACE = "UserMapper.";

    public UserInfoVO selectUserInfoOne(String username) {
        UserInfoVO userInfo = null;
        userInfo = sqlSession.selectOne(NAMESPACE+"getUserInfos", username);
        LOG.info(userInfo.toString());
        return userInfo;
    }

    public List<String> selectUserAuthOne(String username){
        List<String> authList = new ArrayList<String>();
        authList = sqlSession.selectList(NAMESPACE+"getUserAuth", username);
        return authList;
    }
}
