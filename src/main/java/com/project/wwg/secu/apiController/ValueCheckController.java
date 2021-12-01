package com.project.wwg.secu.apiController;

import com.project.wwg.secu.dao.ValueCheckDao;
import com.project.wwg.secu.service.ValueCheckService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/user")
public class ValueCheckController {
    private static final Log LOG = LogFactory.getLog(ValueCheckDao.class);

    ValueCheckService valueCheckService;

    ValueCheckController(ValueCheckService valueCheckService){
        this.valueCheckService = valueCheckService;
    }
    @RequestMapping("/userCheck")
    public ResponseEntity<String> checkUserName(HttpServletRequest request){
        String userName = request.getParameter("userName");
        LOG.info(userName);
        String result = valueCheckService.isUserHere(userName);
        LOG.info(result);
        return ResponseEntity.ok(result);
    }
}
