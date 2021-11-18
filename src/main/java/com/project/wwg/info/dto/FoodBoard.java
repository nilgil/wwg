package com.project.wwg.info.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FoodBoard {
	private int food_no;
	private String username;
	private String food_title;
	private String food_content;
	private String food_photo;
	private Date food_regdate;
	private int food_hit;
	private int food_like;
	
	// page
	private int startRow;
	private int endRow;
	
	// 검색
	private String search;
	private String keyword;
	
//	public int getFood_no() {
//		return food_no;
//	}
//	public void setFood_no(int food_no) {
//		this.food_no = food_no;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getFood_title() {
//		return food_title;
//	}
//	public void setFood_title(String food_title) {
//		this.food_title = food_title;
//	}
//	public String getFood_content() {
//		return food_content;
//	}
//	public void setFood_content(String food_content) {
//		this.food_content = food_content;
//	}
//	public String getFood_photo() {
//		return food_photo;
//	}
//	public void setFood_photo(String food_photo) {
//		this.food_photo = food_photo;
//	}
//	public Date getFood_regdate() {
//		return food_regdate;
//	}
//	public void setFood_regdate(Date food_regdate) {
//		this.food_regdate = food_regdate;
//	}
//	public int getHit() {
//		return hit;
//	}
//	public void setHit(int hit) {
//		this.hit = hit;
//	}
//	public int getStartRow() {
//		return startRow;
//	}
//	public void setStartRow(int startRow) {
//		this.startRow = startRow;
//	}
//	public int getEndRow() {
//		return endRow;
//	}
//	public void setEndRow(int endRow) {
//		this.endRow = endRow;
//	}
//	public String getSearch() {
//		return search;
//	}
//	public void setSearch(String search) {
//		this.search = search;
//	}
//	public String getKeyword() {
//		return keyword;
//	}
//	public void setKeyword(String keyword) {
//		this.keyword = keyword;
//	}
	

}
