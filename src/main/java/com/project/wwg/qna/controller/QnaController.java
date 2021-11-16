package com.project.wwg.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.wwg.qna.service.QnaService;

@Controller
public class QnaController{
	
	@Autowired
	private QnaService qs; //service 인터페이스
	
	@RequestMapping("mainPage.do")
	public String mainPage() {
		return "mainPage";
	}
	
}