package com.project.wwg.info.controller;

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
	public String foodrelist(@PathVariable int food_no, Model model) {
		FoodBoard foodboard = fs.select(food_no);
		List<ReFoodBoard> foodrelist = rfs.foodlist(food_no);
		model.addAttribute("foodrelist", foodrelist);
		model.addAttribute("foodboard", foodboard);
		return "info/foodrelist";
	}

	// 댓글 저장
	@RequestMapping("sInsert")
	public String sInsert(ReFoodBoard ReFoodBoard, Model model) {
		rfs.insert(ReFoodBoard);
		return "redirect:foodrelist/food_no/" + ReFoodBoard.getRfood_no();
	}

	@RequestMapping("repDelete")
	public String delete(ReFoodBoard ReFoodBoard, Model model) {
		rfs.delete(ReFoodBoard.getFood_re_no());
		return "redirect:foodrelist/food_no/" + ReFoodBoard.getRfood_no();
	}

	@RequestMapping("repUpdate")
	public String repUpdate(ReFoodBoard ReFoodBoard, Model model) {
		rfs.update(ReFoodBoard);
		return "redirect:foodrelist/food_no/" + ReFoodBoard.getRfood_no();
	}
	
}
