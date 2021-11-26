package com.project.wwg.admin.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeleteMemberDao {

    protected static final Log LOG = LogFactory.getLog(DeleteMemberDao.class);
    @Autowired
    protected SqlSession sqlSession;
    protected final String NAMESPACE = "AdminMapper.";

    public void deleteMember(String username){
        sqlSession.delete(NAMESPACE+"deleteUserAuth", username);
        sqlSession.delete(NAMESPACE+"deleteUsername", username);
    }
}
