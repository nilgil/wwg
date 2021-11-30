package com.project.wwg.info.dto;

import java.util.Date;

import lombok.Data;

@Data
public class StayBoard {
	private int stay_no;
	private String username;
	private String stay_title;
	private String stay_content;
	private Date stay_regdate;
	private int stay_hit;
	private int stay_like;
	
	// page
	private int startRow;
	private int endRow;
	
	// 검색
	private String search;
	private String keyword;

}
