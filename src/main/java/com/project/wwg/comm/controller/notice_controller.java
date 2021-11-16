package com.project.wwg.comm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.wwg.comm.model.notice;
import com.project.wwg.comm.service.notice_service;

@Controller
public class notice_controller {

	@Autowired
	private notice_service ns;

	@RequestMapping("/comm_list")
	public String initList() {
		return "comm/comm_noticelist";
	}

}
