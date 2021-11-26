package com.project.wwg.secu.service;

import com.project.wwg.secu.controller.SecuController;
import com.project.wwg.secu.dao.UserSignUpDao;
import com.project.wwg.secu.dto.UserDetailsDto;
import com.project.wwg.secu.dto.UsersDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpService {
    private static final Log LOG = LogFactory.getLog(UserSignUpService.class);
    private final UserSignUpDao userSignUpDao;

    public UserSignUpService(UserSignUpDao userSignUpDao) {
         this.userSignUpDao = userSignUpDao;
    }

    //db에 정보를 입력하는 메소드
    public boolean userSignUp(String username, String password,
                              String realname, String location){
        UsersDto users = new UsersDto();
        //1.회원가입 정보 수신 확인
        LOG.info(username +", "+ password+", "+ realname+", "+ location);

        //2.비밀번호 암호화 메소드 실행
        password=modifyPassword(password);

        //3.DB또는 메모리에 유저정보가 있는지 확인(메모리는 나중에)
        //있으면 false리턴 아니면 4번 실행
        if(userSignUpDao.isUserHere(username, users)){
            return false;
        }
        //4.DB에 username, password 및 권한 저장 후 true리턴
        else{
            users.setUsername(username);
            users.setPassword(password);
            users.setLocation(location);
            users.setRealname(realname);
            userSignUpDao.addUser(users);
            return true;
        }
    }

    //패스워드 앞에 {noop}를 붙이는 메드(나중에 암호화 기능으로 변경예정)
    public String modifyPassword(String password){

        return "{noop}"+password;
    }
}
