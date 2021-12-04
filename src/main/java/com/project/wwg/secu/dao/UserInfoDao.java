package com.project.wwg.secu.dao;

import com.project.wwg.secu.dto.UsersDto;
import com.project.wwg.secu.service.UserInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDao {
    private static final Log LOG = LogFactory.getLog(UserInfoDao.class);

    SqlSession sqlSession;
    private final String NAMESPACE = "UserMapper.";

    public UserInfoDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public UsersDto getUserInfo(UsersDto usersDto,String username){
        LOG.info("DAO진입");
        usersDto=sqlSession.selectOne(NAMESPACE+"getUserInfo", username);
        LOG.info(usersDto.getLocation());
        return usersDto;
    }
}
