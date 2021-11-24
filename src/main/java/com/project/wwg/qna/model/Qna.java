package com.project.wwg.qna.model;

import java.sql.Date;

import lombok.Data;


public class Qna{
	
    private int qna_no;
    private String username;
    private String qna_title;
    private String qna_content;
    private Date qna_regdate; //시간
    private int qna_re_ref;  //부모 댓글 번호
    private int qna_re_lev;  //자식 댓글 순서
    private int qna_re_seq;  //댓글 오름차순 순서
    private int qna_hit;     //조회수
    
	// page
	private int startRow;
	private int endRow;
	
    //검색
    private String search;
    private String keyword;
    
    
	public int getQna_no() {
		return qna_no;
	}
	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public Date getQna_regdate() {
		return qna_regdate;
	}
	public void setQna_regdate(Date qna_regdate) {
		this.qna_regdate = qna_regdate;
	}
	public int getQna_re_ref() {
		return qna_re_ref;
	}
	public void setQna_re_ref(int qna_re_ref) {
		this.qna_re_ref = qna_re_ref;
	}
	public int getQna_re_lev() {
		return qna_re_lev;
	}
	public void setQna_re_lev(int qna_re_lev) {
		this.qna_re_lev = qna_re_lev;
	}
	public int getQna_re_seq() {
		return qna_re_seq;
	}
	public void setQna_re_seq(int qna_re_seq) {
		this.qna_re_seq = qna_re_seq;
	}
	public int getQna_hit() {
		return qna_hit;
	}
	public void setQna_hit(int qna_hit) {
		this.qna_hit = qna_hit;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
    
    

	
    
    
}