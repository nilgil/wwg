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
		System.out.println("qnawriteform성공");
		return "qna/qnaWrite";
	}
	
	//글작성
	@RequestMapping("qnawrite.do")
	public String qnainsert(Qna qna, Model model) throws Exception {
		
		System.out.println("qnawrite까지는 왔음");
		
		//글작성성공여부확인하기위해서
		int result = qs.insert(qna);
		
		System.out.println("service에 있는 insert사용하겠다고신호OK");
		
		//값 공유
		model.addAttribute("result", result);
		
		System.out.println("result값 공유했음");
		
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