package com.project.wwg.secu.service;

import com.project.wwg.secu.dao.TestDao;
import com.project.wwg.secu.dto.UserDetailsDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsServiceCustom")
public class UserDetailsServiceCustom implements UserDetailsService {
    private static final Log LOG = LogFactory.getLog(TestDao.class);
    private SqlSession sqlSession;
    private final String NAMESPACE = "UserMapper.";

    @Autowired
    UserDetailsServiceCustom(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    @Override
    public UserDetails loadUserByUsername(String inputUserId) throws UsernameNotFoundException {
        LOG.info("UserDetails 시작");
        // 최종적으로 리턴해야할 객체
        UserDetailsDto userDetails = null;
        //권한을 받아오는 객체
        List<String> authList = null;

        // 사용자 정보 select
        userDetails = sqlSession.selectOne(NAMESPACE+"getUserInfos", inputUserId);
        authList = sqlSession.selectList(NAMESPACE+"getUserAuth", inputUserId);

        // 사용자 정보 없으면 null 처리
        if (userDetails == null) {
            throw new UsernameNotFoundException(inputUserId);

            // 사용자 정보 있을 경우 로직 전개 (userDetails에 데이터 넣기)
        } else {
            userDetails.setAuthorities(authList);
            return userDetails;
        }
    }
}
