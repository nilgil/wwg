package com.project.wwg.info.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 글목록 첫페이지 실행
	@RequestMapping("foodlist.do")
	public String initList() {
		return "redirect:foodlist.do/pageNum/1";
	}
	
	// 글목록
	@RequestMapping("foodlist.do/pageNum/{pageNum}")
	public String foodlist(@PathVariable String pageNum, FoodBoard foodboard, Model model) {
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
	
	// 상세페이지
	@RequestMapping("foodcontent.do/food_no/{food_no}/pageNum/{pageNum}")
	public String foodcontent(@PathVariable int food_no, @PathVariable String pageNum, Model model) {
		service.selectUpdate(food_no);					// 조회수 1증가
		FoodBoard foodboard = service.select(food_no);	// 상세 정보 구하기
		model.addAttribute("foodboard", foodboard);
		model.addAttribute("pageNum", pageNum);
		return "info/foodcontent";
	}
	
	// 글수정 폼
	@RequestMapping("foodupdateform.do/food_no/{food_no}/pageNum/{pageNum}")
	public String foodupdateform(@PathVariable int food_no, @PathVariable String pageNum, Model model) {
		FoodBoard foodboard = service.select(food_no);
		model.addAttribute("foodboard", foodboard);
		model.addAttribute("pageNum", pageNum);
		return "info/foodupdateform";
	}
	
	//글수정
	@RequestMapping("foodupdate.do/pageNum/{pageNum}")
	public String foodupdate(FoodBoard foodboard, @PathVariable String pageNum, Model model) {
		int result = service.update(foodboard);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "info/foodupdate";
	}
	
	// 글삭제 폼
	@RequestMapping("fooddeleteform.do")
//	public String fooddeleteform(@PathVariable int food_no, @PathVariable String pageNum, Model model) {
	public String fooddeleteform() {
//		int result = service.delete(food_no);
//		model.addAttribute("result", result);
//		model.addAttribute("pageNum", pageNum);
		return "info/fooddeleteform";
	}

	// 글삭제
	@PostMapping("fooddelete.do")
	public String fooddelete(@RequestParam int food_no, @RequestParam String pageNum, Model model) {
		System.out.println("food_no:"+food_no);
		int result = service.delete(food_no);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "info/fooddelete";
	}
	
	// 좋아요
    @RequestMapping("foodlike.do")
    public String recommend (@RequestParam int food_no, Model model) {
		service.like(food_no);					// 좋아요 1증가
        model.addAttribute("food_no", food_no);
        return "redirect:foodlist.do";
    }

	
}
