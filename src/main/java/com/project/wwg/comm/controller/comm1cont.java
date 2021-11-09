package com.project.wwg.comm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.wwg.comm.service.comm1service;

@Controller
public class comm1cont {


	@RequestMapping("/comm1")
	public String comm1() {
		return "/commview/comm1";
	}
	
}
