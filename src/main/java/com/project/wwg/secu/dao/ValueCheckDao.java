package com.project.wwg.secu.dao;

import com.project.wwg.secu.dto.UsersDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ValueCheckDao {
    private static final Log LOG = LogFactory.getLog(ValueCheckDao.class);
    SqlSession sqlSession;
    private final String NAMESPACE = "UserMapper.";

    public ValueCheckDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public boolean isUserHere(String username){
        username = sqlSession.selectOne(NAMESPACE+"isUserHere",username);
        LOG.info(username == null);
        return username == null;
    }
}
