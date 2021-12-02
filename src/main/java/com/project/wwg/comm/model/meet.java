package com.project.wwg.comm.model;

import java.util.Date;

import lombok.Data;

@Data
public class meet {
	
	private int meet_no;  //num         // 후기게시판 번호
	private String member_id;  //writer     // foreign key
	private String meet_title;    // 후기게시판 제목
	private String meet_content;  // 후기게시판 내용
	private Date meet_regdate;    // 후기게시판 생성일자
	private int meet_hit;         // 후기게시판 조회수
	private int meet_like;
	
	// page
	private int startRow;
	private int endRow;
	
	// 검색
	private String search;
	private String keyword;

}