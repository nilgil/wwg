package com.project.wwg.info.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.wwg.info.dto.FoodBoard;
import com.project.wwg.info.service.FoodBoardService;

@Controller
public class FoodBoardController {

	@Autowired
	private FoodBoardService service;
	
	// 글작성 폼
	@RequestMapping("foodform.do")
	public String foodform() {
		return "info/foodform";
	}
	
	// 글작성
	@RequestMapping("foodwrite.do")
	public String foodwrite(FoodBoard foodboard, Model model) {
		System.out.println("in");
		System.out.println("food_title:"+foodboard.getFood_title());
		
		int result = service.insert(foodboard);
		
		model.addAttribute("result", result);
		
		return "info/insertresult";
	}
	
	// 글목록
	@RequestMapping("foodlist.do")
	public String foodlist(HttpServletRequest request, Model model) {
		int page = 1;		// 현재 페이지 번호
		int limit = 10;		// 한 페이지에 출력할 데이터 갯수
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 총 데이터 갯수
		int listcount = service.getCount();
		
		List<FoodBoard> foodlist = service.getFoodList(page);
		
		// 총 페이지
		int pageCount = listcount/limit+((listcount%limit==0)? 0 : 1);
		
		int startPage = ((page-1)/10 * limit + 1);
		int endPage = startPage + 10 - 1;
		
		if(endPage > pageCount)
			endPage = pageCount;
		
		model.addAttribute("page", page);
		model.addAttribute("listcount", listcount);
		model.addAttribute("foodlist", foodlist);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "info/foodlist";
	}
	
}
