package com.project.wwg.comm.model;

import java.sql.Date;

import lombok.Data;

@Data
public class review_replydto {
	
	private int review_re_no;  //rno        // 후기게시판 댓글의 번호
	private int review_no;     //bno        // 후기게시판 게시글의 번호
	private String member_id;   //replyer       // foreign key
	private String review_re_content; //replytext // 후기게시판 내용
	private Date review_re_regdate;    // 후기게시판 생성일자
	
}