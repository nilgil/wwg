package com.project.wwg.admin.service;

import com.project.wwg.admin.dao.DeleteMemberDao;
import com.project.wwg.admin.pageController.AdminPageController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class MemberDeleteService {
    private static final Log LOG = LogFactory.getLog(MemberDeleteService.class);

    DeleteMemberDao deleteMemberDao;

    MemberDeleteService(DeleteMemberDao deleteMemberDao){
        this.deleteMemberDao=deleteMemberDao;
    }

    public void deleteMember(String username){
        deleteMemberDao.deleteMember(username);
    }

}
