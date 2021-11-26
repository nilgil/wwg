package com.project.wwg.admin.pageController;

import com.project.wwg.admin.service.MemberListTakingService;
import com.project.wwg.secu.dto.UsersDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    private static final Log LOG = LogFactory.getLog(AdminPageController.class);

    MemberListTakingService memberListTakingService;

    AdminPageController(MemberListTakingService memberListTakingService){
        this.memberListTakingService=memberListTakingService;
    }

    @GetMapping("/memberList")
    public String memberList(Model model) {
        List<UsersDto> userList = new ArrayList<UsersDto>();
        LOG.info("aproach to memberList");
        userList =memberListTakingService.getUserList(userList);
        userList.forEach(e->{
            LOG.info(e.getUsername());
        });
        LOG.info("end");
        model.addAttribute("memberList", userList);
        return"admin/memberList";
    }
}
