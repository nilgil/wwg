package com.project.wwg.comm.commcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class comm1cont {

	@Autowired
	
	
	@RequestMapping("/comm1")
	public String comm1() {
		return "/commview/comm1";
	}
	
}
