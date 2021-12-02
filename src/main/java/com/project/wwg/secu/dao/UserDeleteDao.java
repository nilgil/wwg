package com.project.wwg.secu.dao;

import com.project.wwg.secu.dto.UsersDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserDeleteDao {
    private static final Log LOG = LogFactory.getLog(UserInfoUpdateDao.class);
    SqlSession sqlSession;
    private final String NAMESPACE = "UserMapper.";

    public UserDeleteDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void userQuit(UsersDto usersDto){
        sqlSession.delete(NAMESPACE+"removeAuth",usersDto);
        sqlSession.delete(NAMESPACE+"removeUser",usersDto);
    }
}
