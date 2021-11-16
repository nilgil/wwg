package com.project.wwg.secu.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper {
    private static final Log LOG = LogFactory.getLog(UserMapper.class);
    private SqlSession sqlSession;

    @Autowired
    UserMapper(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }
    private final String NAMESPACE = "secuMapper.";
}
