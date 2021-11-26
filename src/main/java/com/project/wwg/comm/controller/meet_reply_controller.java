package com.project.wwg.comm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//Board;
import com.project.wwg.comm.model.meet;

//ReplyBoard;
import com.project.wwg.comm.model.meet_replydto;

//BoardService;
import com.project.wwg.comm.service.meet_service;

//ReplyBoardService;
import com.project.wwg.comm.service.meet_reply_service;

@Controller
public class meet_reply_controller {
	@Autowired
	private meet_reply_service mrs;
	@Autowired
	private meet_service ms;

	// 댓글 목록 구하기
	@RequestMapping("/meet_reply/meet_no/{meet_no}")
	public String slist(@PathVariable int meet_no, Model model) {
		meet meet = ms.select(meet_no);
		List<meet_replydto> meet_reply = mrs.list(meet_no);
		model.addAttribute("meet_reply", meet_reply);
		model.addAttribute("meet", meet);
		return "comm/meet/meet_reply";
	}

	// 댓글 저장
	@RequestMapping("/meet_reply_insert")
	public String insert(meet_replydto rrdto, Model model) {
		mrs.insert(rrdto);
		return "redirect:meet_reply/meet_no/" + rrdto.getMeet_fno();
	}

	@RequestMapping("/meet_reply_delete")
	public String delete(meet_replydto rr, Model model) {
		mrs.delete(rr.getMeet_re_no());
		return "redirect:meet_reply/meet_no/" + rr.getMeet_fno();
	}

	@RequestMapping("/meet_reply_update")
	public String update(meet_replydto rr, Model model) {
		mrs.update(rr);
		return "redirect:meet_reply/meet_no/" + rr.getMeet_fno();
	}
}