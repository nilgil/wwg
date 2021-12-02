package com.project.wwg.info.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.wwg.info.dto.FoodBoard;
import com.project.wwg.info.dto.ReFoodBoard;
import com.project.wwg.info.service.FoodBoardService;
import com.project.wwg.info.service.ReFoodBoardService;

@Controller
public class ReFoodBoardController {
	@Autowired
	private ReFoodBoardService rfs;
	@Autowired
	private FoodBoardService fs;
	
	// 댓글 목록 구하기
	@RequestMapping("foodrelist/food_no/{food_no}")
	public String foodrelist(@PathVariable int food_no, Model model, Principal principal) {
		FoodBoard foodboard = fs.select(food_no);
		List<ReFoodBoard> foodrelist = rfs.foodlist(food_no);
		
		String username = "guest";
		if(principal != null) {			// 비로그인시 상세정보 구하기
		username = principal.getName();
		}
		
		model.addAttribute("username", username);
		model.addAttribute("foodrelist", foodrelist);
		model.addAttribute("foodboard", foodboard);
		 		
		return "info/food/foodrelist";
	}

	// 댓글 저장
	@RequestMapping("foodreInsert")
	public String foodreInsert(Principal principal ,ReFoodBoard ReFoodBoard, Model model) {
		rfs.insert(ReFoodBoard);

		/*
		 * String username = principal.getName(); // 로그인후 유저네임 넘기기
		 */
		
		/*
		 * model.addAttribute("username", username); // 로그인후 유저네임 넘기기
		 */		
		 return "redirect:foodrelist/food_no/" + ReFoodBoard.getRfood_no();
	}

	@RequestMapping("foodreDelete")
	public String fooddelete(ReFoodBoard ReFoodBoard, Model model) {
		rfs.delete(ReFoodBoard.getFood_re_no());
		return "redirect:foodrelist/food_no/" + ReFoodBoard.getRfood_no();
	}

	@RequestMapping("foodreUpdate")
	public String foodreUpdate(ReFoodBoard ReFoodBoard, Model model) {
		rfs.update(ReFoodBoard);
		return "redirect:foodrelist/food_no/" + ReFoodBoard.getRfood_no();
	}
	
}
