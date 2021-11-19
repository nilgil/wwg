package com.project.wwg.comm.model;

import java.sql.Date;

import lombok.Data;

@Data
public class review_reply {
	
	private int review_re_no;          // 후기게시판 댓글의 번호
	private int review_no;             // 후기게시판 게시글의 번호
	private String member_id;          // foreign key
	private String review_re_content;  // 후기게시판 내용
	private Date review_re_regdate;    // 후기게시판 생성일자
	
	// page
	private int startRow;
	private int endRow;
	

}