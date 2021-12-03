package com.project.wwg.comm.controller;

import java.security.Principal;
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


@Controller
public class notice_controller {

	@Autowired
	private notice_service ns;
	
	// 글목록 첫페이지 실행
		@RequestMapping("noticelist")
		public String initList() {
			return "redirect:noticelist/pageNum/1";
		}

	@RequestMapping("/noticelist/pageNum/{pageNum}")
	public String list(Principal principal, @PathVariable String pageNum, notice notice, Model model) {
		String username = "guest";
		if(principal != null) {
			username=principal.getName();
		};
		
		final int rowPerPage = 10;	// 화면에 출력할 데이터 갯수
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum); // 현재 페이지 번호
		
		// int total = bs.getTotal();
		int total = ns.getTotal(notice); // 검색 (데이터 갯수)
		
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		notice_PagingPgm pp = new notice_PagingPgm(total, rowPerPage, currentPage);
		notice.setStartRow(startRow);
		notice.setEndRow(endRow);
		// List<Board> list = bs.list(startRow, endRow);
		int no = total - startRow + 1;		// 화면 출력 번호
		List<notice> list = ns.list(notice);
		
		model.addAttribute("username", username);
		model.addAttribute("list", list);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);
		
		model.addAttribute("admin", "user2");
		// 검색
		model.addAttribute("search", notice.getSearch());
		model.addAttribute("keyword", notice.getKeyword());
		
		return "comm/notice/comm_noticelist";
	}

	// 글작성 폼
		@RequestMapping("/comm_noticeinsertform")
		public String comm_noticeinsertform(Principal principal, Model model) {
			String username = "guest";
			if(principal != null) {
				username=principal.getName();
			};
			model.addAttribute("username", username);
			return "comm/notice/comm_noticeinsertform";
		}
		
	// 글작성
		@RequestMapping("/comm_noticeinsert")
		public String noticeinsert(Principal principal, notice notice, Model model) {

			System.out.println("제목:"+notice.getNotice_title());
			System.out.println("내용:"+notice.getNotice_content());
			
			String username = principal.getName(); // 로그인후 유저네임 넘기기
			System.out.println("username:"+username);
			notice.setMember_id(username); // 로그인후 유저네임 넘기기
			
			int notice_no = notice.getNotice_no();
			int number = ns.getMaxNum();
			notice.setNotice_no(number);
			int result = ns.insert(notice);
			
			model.addAttribute("result", result);
			
			return "comm/notice/comm_noticeinsertalert";
		}
	// 상세페이지	
		@RequestMapping("/noticeview/notice_no/{notice_no}/pageNum/{pageNum}")
		public String view(Principal principal, @PathVariable int notice_no, @PathVariable String pageNum, Model model) {
			String username = "guest";
			if(principal != null) {
				username=principal.getName();
			};
			ns.selectUpdate(notice_no);
			notice notice = ns.select(notice_no);
			model.addAttribute("username", username);
			model.addAttribute("notice", notice);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("admin", "user2");
			return "comm/notice/comm_noticeview";
		}	
	// 수정폼	
		@RequestMapping("/noticeupdateform/notice_no/{notice_no}/pageNum/{pageNum}")
		public String updateForm(Principal principal, @PathVariable int notice_no, @PathVariable String pageNum, Model model) {
			String username = "guest";
			if(principal != null) {
				username=principal.getName();
			};
			model.addAttribute("username", username);
			notice notice = ns.select(notice_no);
			model.addAttribute("notice", notice);
			model.addAttribute("pageNum", pageNum);
			return "comm/notice/comm_noticeupdateform";
		}
	// 수정	
		@RequestMapping("/noticeupdate/pageNum/{pageNum}")
		public String update(notice notice, @PathVariable String pageNum, Model model) {
			int result = ns.update(notice);
			model.addAttribute("result", result);
			model.addAttribute("pageNum", pageNum);
			return "comm/notice/comm_noticeupdate";
		}
		
	// 삭제폼	
//		@RequestMapping("/noticedeleteForm/notice_no/{notice_no}/pageNum/{pageNum}")
//		public String deleteForm(@PathVariable int notice_no, @PathVariable String pageNum, Model model) {
//			notice notice = ns.select(notice_no);
//			model.addAttribute("notice", notice);
//			model.addAttribute("pageNum", pageNum);
//			return "comm/comm_noticedeleteform";
//		}
		
    // 삭제
		@RequestMapping("/noticedelete/notice_no/{notice_no}/pageNum/{pageNum}")
		public String delete(@PathVariable int notice_no, String pageNum, Model model) {
			int result = ns.delete(notice_no);
			model.addAttribute("result", result);
			model.addAttribute("pageNum", pageNum);
			return "comm/notice/comm_noticedelete";
		}
		
		
		
		
		
		
}
