package com.project.wwg.info.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.wwg.info.dto.TourBoard;
import com.project.wwg.info.dto.ReTourBoard;
import com.project.wwg.info.service.TourBoardService;
import com.project.wwg.info.service.ReTourBoardService;

@Controller
public class ReTourBoardController {
	@Autowired
	private ReTourBoardService rfs;
	@Autowired
	private TourBoardService fs;
	
	// 댓글 목록 구하기
	@RequestMapping("tourrelist/tour_no/{tour_no}")
	public String tourrelist(@PathVariable int tour_no, Model model, Principal principal) {
		TourBoard tourboard = fs.select(tour_no);
		List<ReTourBoard> tourrelist = rfs.tourlist(tour_no);
		
		/*
		 * String username = principal.getName(); // 로그인후 유저네임 넘기기
		 */		
		model.addAttribute("tourrelist", tourrelist);
		model.addAttribute("tourboard", tourboard);
		/*
		 * model.addAttribute("username", username); // 로그인후 유저네임 넘기기
		 */		
		return "info/tour/tourrelist";
	}

	// 댓글 저장
	@RequestMapping("tourreInsert")
	public String tourreInsert(Principal principal ,ReTourBoard ReTourBoard, Model model) {
		rfs.insert(ReTourBoard);

		/*
		 * String username = principal.getName(); // 로그인후 유저네임 넘기기
		 */
		
		/*
		 * model.addAttribute("username", username); // 로그인후 유저네임 넘기기
		 */		
		 return "redirect:tourrelist/tour_no/" + ReTourBoard.getRtour_no();
	}

	@RequestMapping("tourreDelete")
	public String tourdelete(ReTourBoard ReTourBoard, Model model) {
		rfs.delete(ReTourBoard.getTour_re_no());
		return "redirect:tourrelist/tour_no/" + ReTourBoard.getRtour_no();
	}

	@RequestMapping("tourreUpdate")
	public String tourreUpdate(ReTourBoard ReTourBoard, Model model) {
		rfs.update(ReTourBoard);
		return "redirect:tourrelist/tour_no/" + ReTourBoard.getRtour_no();
	}
	
}
