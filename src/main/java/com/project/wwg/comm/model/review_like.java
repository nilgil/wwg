package com.project.wwg.comm.model;

import lombok.Data;

@Data
public class review_like {
	
	private int review_no;          // 후기게시판 번호
	private String member_id;       // foreign key
	private int review_like;    // 후기게시판 좋아요
	
}