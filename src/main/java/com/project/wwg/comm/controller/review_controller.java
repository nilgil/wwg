package com.project.wwg.comm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.wwg.comm.model.review;
import com.project.wwg.comm.service.review_PagingPgm;
import com.project.wwg.comm.service.review_service;

@Controller
public class review_controller {

	@Autowired
	private review_service rs;
	
	// 글목록 첫페이지 실행
		@RequestMapping("reviewlist")
		public String initList() {
			return "redirect:reviewlist/pageNum/1";
		}

	@RequestMapping("/reviewlist/pageNum/{pageNum}")
	public String list(@PathVariable String pageNum, review review, Model model) {
		
		final int rowPerPage = 10;	// 화면에 출력할 데이터 갯수
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum); // 현재 페이지 번호
		
		// int total = bs.getTotal();
		int total = rs.getTotal(review); // 검색 (데이터 갯수)
		
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		review_PagingPgm pp = new review_PagingPgm(total, rowPerPage, currentPage);
		review.setStartRow(startRow);
		review.setEndRow(endRow);
		// List<Board> list = bs.list(startRow, endRow);
		int no = total - startRow + 1;		// 화면 출력 번호
		List<review> list = rs.list(review);
		
		model.addAttribute("list", list);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);
		// 검색
		model.addAttribute("search", review.getSearch());
		model.addAttribute("keyword", review.getKeyword());
		
		return "comm/review/review_list";
	}

	// 글작성 폼
		@RequestMapping("/comm_reviewinsertform")
		public String review_insertform() {
			return "comm/review/review_insertform";
		}
		
	// 글작성
		@RequestMapping("/comm_reviewinsert")
		public String reviewinsert(review review, Model model) {

			System.out.println("제목:"+review.getReview_title());
			System.out.println("내용:"+review.getReview_content());
			
			int review_no = review.getReview_no();
			int number = rs.getMaxNum();
			review.setReview_no(number);
			int result = rs.insert(review);
			
			model.addAttribute("result", result);
			
			return "comm/review/review_insertalert";
		}
	// 상세페이지	
		@RequestMapping("/reviewview/review_no/{review_no}/pageNum/{pageNum}")
		public String view(@PathVariable int review_no, @PathVariable String pageNum, Model model) {
			rs.selectUpdate(review_no);
			review review = rs.select(review_no);
			model.addAttribute("review", review);
			model.addAttribute("pageNum", pageNum);
			return "comm/review/review_view";
		}	
	// 수정폼	
		@RequestMapping("/reviewupdateform/review_no/{review_no}/pageNum/{pageNum}")
		public String updateForm(@PathVariable int review_no, @PathVariable String pageNum, Model model) {
			review review = rs.select(review_no);
			model.addAttribute("review", review);
			model.addAttribute("pageNum", pageNum);
			return "comm/review/review_updateform";
		}
	// 수정	
		@RequestMapping("/reviewupdate/pageNum/{pageNum}")
		public String update(review review, @PathVariable String pageNum, Model model) {
			int result = rs.update(review);
			model.addAttribute("result", result);
			model.addAttribute("pageNum", pageNum);
			return "comm/review/review_update";
		}
		
	// 삭제폼	
//		@RequestMapping("/reviewdeleteForm/review_no/{review_no}/pageNum/{pageNum}")
//		public String deleteForm(@PathVariable int review_no, @PathVariable String pageNum, Model model) {
//			review review = ns.select(review_no);
//			model.addAttribute("review", review);
//			model.addAttribute("pageNum", pageNum);
//			return "comm/comm_reviewdeleteform";
//		}
		
    // 삭제
		@RequestMapping("/reviewdelete/review_no/{review_no}/pageNum/{pageNum}")
		public String delete(@PathVariable int review_no, String pageNum, Model model) {
			int result = rs.delete(review_no);
			model.addAttribute("result", result);
			model.addAttribute("pageNum", pageNum);
			return "comm/review/review_delete";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}