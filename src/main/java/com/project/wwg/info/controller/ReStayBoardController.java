package com.project.wwg.info.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.wwg.info.dto.StayBoard;
import com.project.wwg.info.dto.ReStayBoard;
import com.project.wwg.info.service.StayBoardService;
import com.project.wwg.info.service.ReStayBoardService;

@Controller
public class ReStayBoardController {
	@Autowired
	private ReStayBoardService rfs;
	@Autowired
	private StayBoardService fs;
	
	// 댓글 목록 구하기
	@RequestMapping("stayrelist/stay_no/{stay_no}")
	public String stayrelist(@PathVariable int stay_no, Model model, Principal principal) {
		StayBoard stayboard = fs.select(stay_no);
		List<ReStayBoard> stayrelist = rfs.staylist(stay_no);
		
		/*
		 * String username = principal.getName(); // 로그인후 유저네임 넘기기
		 */		
		model.addAttribute("stayrelist", stayrelist);
		model.addAttribute("stayboard", stayboard);
		/*
		 * model.addAttribute("username", username); // 로그인후 유저네임 넘기기
		 */		
		return "info/stay/stayrelist";
	}

	// 댓글 저장
	@RequestMapping("stayreInsert")
	public String stayreInsert(Principal principal ,ReStayBoard ReStayBoard, Model model) {
		rfs.insert(ReStayBoard);

		/*
		 * String username = principal.getName(); // 로그인후 유저네임 넘기기
		 */
		
		/*
		 * model.addAttribute("username", username); // 로그인후 유저네임 넘기기
		 */		
		 return "redirect:stayrelist/stay_no/" + ReStayBoard.getRstay_no();
	}

	@RequestMapping("stayreDelete")
	public String staydelete(ReStayBoard ReStayBoard, Model model) {
		rfs.delete(ReStayBoard.getStay_re_no());
		return "redirect:stayrelist/stay_no/" + ReStayBoard.getRstay_no();
	}

	@RequestMapping("stayreUpdate")
	public String stayreUpdate(ReStayBoard ReStayBoard, Model model) {
		rfs.update(ReStayBoard);
		return "redirect:stayrelist/stay_no/" + ReStayBoard.getRstay_no();
	}
	
}
