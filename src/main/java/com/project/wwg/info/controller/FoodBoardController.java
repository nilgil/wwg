package com.project.wwg.info.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.wwg.info.dto.FoodBoard;
import com.project.wwg.info.service.FoodBoardService;
import com.project.wwg.info.service.PagingPgm;

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
	public String foodwrite(FoodBoard foodboard, Model model, HttpServletRequest request) {
		System.out.println("in");
		System.out.println("food_title:"+foodboard.getFood_title());
		
		int food_no = foodboard.getFood_no();
		int number = service.getMaxNum();
		foodboard.setFood_no(number);
		int result = service.insert(foodboard);
		model.addAttribute("result", result);
		
		return "info/insertresult";
	}
	
	@RequestMapping("foodlist.do")
	public String initList() {
		return "redirect:foodlist.do/pageNum/1";
	}
	
	// 글목록
	@RequestMapping("foodlist.do/pageNum/{pageNum}")
	public String foodlist(String pageNum, FoodBoard foodboard, Model model) {
		final int rowPerPage = 10;
		if(pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		// 총 데이터 갯수
		int currentPage = Integer.parseInt(pageNum);
		
		int total = service.getTotal(foodboard);	// 검색
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		foodboard.setStartRow(startRow);
		foodboard.setEndRow(endRow);
		
		int no = total - startRow + 1;	// 화면 출력 번호
		List<FoodBoard> foodlist = service.foodlist(foodboard);
		model.addAttribute("foodlist", foodlist);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);
		// 검색
		model.addAttribute("search", foodboard.getSearch());
		model.addAttribute("keyword", foodboard.getKeyword());
		
		return "info/foodlist";
	}
	
}
