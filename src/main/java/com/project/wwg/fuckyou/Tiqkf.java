package com.project.wwg.fuckyou;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Tiqkf {
    private static final Log LOG = LogFactory.getLog(Tiqkf.class );

    @GetMapping({"/"})
    public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("message", "씨발 좃팔 개팔 쌍팔 ㅆ부럴");
        LOG.info("hello 가동");
        return "hello";
    }
}
