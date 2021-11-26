package com.project.wwg.comm.model;

import java.util.Date;

import lombok.Data;

@Data
public class meet_replydto {
	
	private int meet_re_no;  //rno        // 후기게시판 댓글의 번호
	private int meet_fno;     //bno        // 후기게시판 게시글의 번호, foreign key
	private String member_id;   //replyer       // foreign key
	private String meet_re_content; //replytext // 후기게시판 내용
	private Date meet_re_regdate;    // 후기게시판 생성일자
	
}