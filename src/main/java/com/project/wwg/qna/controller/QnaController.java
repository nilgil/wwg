package com.project.wwg.qna.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
		//dto 객체, model은 전달값있을때
		System.out.println("qnawrite까지는 왔음");
		
		//qna_no 증가
		int num = qna.getQna_no();
		System.out.println("qna_no 가져옴");
		
		int number = qs.getMaxNum();
		System.out.println("증가시킨 qna_no값을 number에 할당함");
		
		qna.setQna_no(number);
		System.out.println("number값을 qna_no에 set함");
		
		//글작성성공여부확인하기위해서
		int result = qs.insert(qna);
		System.out.println("service에 있는 insert사용하겠다고신호OK");
		
		//값 공유
		model.addAttribute("result", result);
		
		System.out.println("result값 공유했음");
		
		return "qna/qnaWriteTest";
	}
	
	//글목록list
	@RequestMapping("qnalist.do")
	public String qnalist(Model model, HttpServletRequest request, Qna qna) throws Exception {
		
		List<Qna> qnalist = new ArrayList<Qna>();
		
		int page = 1;   //1번게시물부터
		int limit = 10; //10번게시물까지 출력
		
		if(request.getParameter("page") != null) { //요청받은값이 null값이 아니면?
			page = Integer.parseInt(request.getParameter("page")); //형변환
		}
		
		//1.총 리스트 개수
		int listcount = qs.getListCount();
		
		//2.페이지 번호를를 dao클래스에 전달하기 위해?
		qnalist = qs.getPageList(page);  //list받아오기위한
		
		//3.총 페이지 개수
		int maxpage = (int) ((double) listcount / limit + 0.95);
		
		//4.한개의 page 보여줄 시작페이지 수 (1,11,21,31 ...)
		int startpage = ( ((int) ((double) page / 10 + 0.9)) -1)*10+1;
		
		//5.한개의 page 보여줄 끝페이지 수 (10,20,30,40 ...)
		int endpage = maxpage;
		
		//끝페이지수가 
		if(endpage > startpage + 10 -1) 
			endpage = startpage + 10 -1;
		
		//qna_no값
		int no = qs.getMaxNum();
		
		//list
		List<Qna> qna_list = qs.getPageList(page);
		
		//값공유
		model.addAttribute("page", page); //현재페이지num
		model.addAttribute("startpage", startpage); //시작페이지num
		model.addAttribute("endpage", endpage); //끝페이지num
		model.addAttribute("maxpage", maxpage); //총 게시물개수
		model.addAttribute("listcount", listcount); //총 리스트
		model.addAttribute("qnalist", qnalist); //list화
		model.addAttribute("qna_list", qna_list); //test중
		model.addAttribute("no", no); //qna_no값
		
		return "qna/qnaList";
	}
	
	//글작성 성공시 list넘어감
	@RequestMapping("/qnaList")
	public String insert_qnaList() {
		System.out.println("list 호출성공!");
        return "qna/qnaList";
//		return "redirect:qna/qnaList/page/1";
	}
	
	
	
	
	
	
}