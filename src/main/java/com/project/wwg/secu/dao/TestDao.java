package com.project.wwg.secu.dao;

import com.project.wwg.secu.dto.TestDto;
import com.project.wwg.secu.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
    private static final Log LOG = LogFactory.getLog(TestDao.class);
    private SqlSession sqlSession;

    @Autowired
    TestDao(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }
    private final String NAMESPACE = "secuMapper.";

    public String getUsers(String user){
        LOG.info("DAO!!!"+user);
        TestDto testDto = new TestDto();
        testDto.setUsers(user);
        LOG.info(testDto.getUsers());
        LOG.info(sqlSession.selectOne(NAMESPACE+"getUsers",testDto));
        testDto = sqlSession.selectOne(NAMESPACE+"getUsers",testDto);
        return testDto.getUsers();
    }
}
