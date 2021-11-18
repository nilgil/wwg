package com.project.wwg.comm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.wwg.comm.model.notice;
import com.project.wwg.comm.service.notice_PagingPgm;
import com.project.wwg.comm.service.notice_service;
import com.project.wwg.info.dto.FoodBoard;

@Controller
public class notice_controller {

	@Autowired
	private notice_service ns;

	@RequestMapping("/noticelist")
	public String list(String pageNum, notice board, Model model) {
		
		final int rowPerPage = 10;	// 화면에 출력할 데이터 갯수
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum); // 현재 페이지 번호
		
		// int total = bs.getTotal();
		int total = ns.getTotal(board); // 검색 (데이터 갯수)
		
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		notice_PagingPgm pp = new notice_PagingPgm(total, rowPerPage, currentPage);
		board.setStartRow(startRow);
		board.setEndRow(endRow);
		// List<Board> list = bs.list(startRow, endRow);
		int no = total - startRow + 1;		// 화면 출력 번호
		List<notice> list = ns.list(board);
		
		model.addAttribute("list", list);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);
		// 검색
		model.addAttribute("search", board.getSearch());
		model.addAttribute("keyword", board.getKeyword());
		
		return "comm/comm_noticetest";
	}

	// 글작성 폼
		@RequestMapping("/comm_noticeinsertform")
		public String comm_noticeinsertform() {
			return "comm/comm_noticeinsertform";
		}
		
		// 글작성
		@RequestMapping("/comm_noticeinsert")
		public String noticeinsert(notice board, Model model) {

			System.out.println("제목:"+board.getNotice_title());
			System.out.println("내용:"+board.getNotice_content());
			
			int result = ns.insert(board);
			
			model.addAttribute("result", result);
			
			return "comm/comm_noticetest";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
