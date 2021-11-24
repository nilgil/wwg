package com.project.wwg.comm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//Board;
import com.project.wwg.comm.model.review;

//ReplyBoard;
import com.project.wwg.comm.model.review_reply;

//BoardService;
import com.project.wwg.comm.service.review_service;

//ReplyBoardService;
import com.project.wwg.comm.service.review_reply_service;

@Controller
public class review_reply_controller {
	@Autowired
	private review_reply_service rrs;
	@Autowired
	private review_service rs;

	@RequestMapping("/review_view/review_no/{review_no}")
	public String slist(@PathVariable int review_no, Model model) {
		review review = rs.select(review_no);
		List<review_reply> review_view = rrs.list(review_no);
		model.addAttribute("review_view", review_view);
		model.addAttribute("review", review);
		return "comm/review/review_view";
	}

	@RequestMapping("/review_reply_insert")
	public String sInsert(review_reply rr, Model model) {
		rrs.insert(rr);
		return "redirect:comm/review/review_view/review_no/" + rr.getReview_no();
	}

	@RequestMapping("/review_reply_delete")
	public String delete(review_reply rr, Model model) {
		rrs.delete(rr.getReview_re_no());
		return "redirect:comm/review/review_view/review_no/" + rr.getReview_no();
	}

	@RequestMapping("/review_reply_update")
	public String repUpdate(review_reply rr, Model model) {
		rrs.update(rr);
		return "redirect:comm/review/review_view/review_no/" + rr.getReview_no();
	}
}