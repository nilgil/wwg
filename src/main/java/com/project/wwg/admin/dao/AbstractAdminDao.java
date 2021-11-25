package com.project.wwg.admin.dao;

import com.project.wwg.secu.dao.UserSignUpDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractAdminDao {
    private static final Log LOG = LogFactory.getLog(AbstractAdminDao.class);
    SqlSession sqlSession;
    protected final String NAMESPACE = "adminMapper.";
}
