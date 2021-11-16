package com.project.wwg.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.wwg.qna.model.Qna;
import com.project.wwg.qna.service.QnaService;

@Controller
public class QnaController{
	
	@Autowired
	private QnaService qs; //service 인터페이스
	
	//글작성폼
	@RequestMapping("qnawriteform.do")
	public String qnawriteform() {
		return "qna/qnaWrite";
	}
	
	//글작성
	@RequestMapping("qnawrite.do")
	public String qnainsert(Qna qna, Model model) {
		
		//글작성성공여부확인하기위해서
		int result = qs.insert(qna);
		
		//값 공유
		model.addAttribute("result", result);
		
		return "qna/qnaWriteTest";
	}
	
	
//	@RequestMapping("mainPage.do")
//	public String mainPage() {
//		return "mainPage";
//	}
	
//	@RequestMapping("/insert")
//	public String insert(Qna qna) {
//		int qna_no = qna.getQna_no();
//		int number = qs.getMaxNum();
//		qs.insert(qna);
//		
//		return "write_alert";
//	}
	
}