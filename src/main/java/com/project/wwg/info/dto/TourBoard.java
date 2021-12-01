package com.project.wwg.info.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TourBoard {
	private int tour_no;
	private String username;
	private String tour_title;
	private String tour_content;
	private Date tour_regdate;
	private int tour_hit;
	private int tour_like;
	
	// page
	private int startRow;
	private int endRow;
	
	// 검색
	private String search;
	private String keyword;

}
