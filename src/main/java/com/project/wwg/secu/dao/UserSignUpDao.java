package com.project.wwg.secu.dao;

import com.project.wwg.secu.dto.UserDetailsDto;
import com.project.wwg.secu.service.UserSignUpService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserSignUpDao {
    private static final Log LOG = LogFactory.getLog(UserSignUpDao.class);
    SqlSession sqlSession;
    private final String NAMESPACE = "UserMapper.";

    public UserSignUpDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public boolean isUserHere(String username,UserDetailsDto userDetails){
        userDetails = sqlSession.selectOne(NAMESPACE+"getUserInfos", username);
        LOG.info(userDetails != null);
        return userDetails != null;
    }

    public void addUser(UserDetailsDto userDetails){
        sqlSession.insert(NAMESPACE+"addUser", userDetails);
    }
}
