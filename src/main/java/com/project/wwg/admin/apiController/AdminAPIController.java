package com.project.wwg.admin.apiController;

import com.project.wwg.admin.pageController.AdminPageController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api")
public class AdminAPIController {
    private static final Log LOG = LogFactory.getLog(AdminAPIController.class);

    //로그인 필요
    @DeleteMapping("/deleteMember")
    public ResponseEntity<String> deleteMember(@RequestParam(name = "username")String username ){
        LOG.info("deleteMember Start!!"+username);


        return ResponseEntity.ok().body("success");
    }
}
