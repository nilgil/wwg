package com.project.wwg.secu.dao;

import com.project.wwg.secu.dto.UsersDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoUpdateDao {
    private static final Log LOG = LogFactory.getLog(UserInfoUpdateDao.class);
    SqlSession sqlSession;
    private final String NAMESPACE = "UserMapper.";

    public UserInfoUpdateDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void userInfoUpdate(String username, String password, String realname, String location){
        UsersDto usersDto = new UsersDto();
        usersDto.setUsername(username);
        usersDto.setPassword("{noop}"+password);
        usersDto.setRealname(realname);
        usersDto.setLocation(location);
        sqlSession.update(NAMESPACE+"updateInfo", usersDto);
    }
}
