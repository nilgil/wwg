package com.project.wwg.comm.model;

import java.sql.Date;

public class notice {

	private int notice_no;          // 공지사항 번호
	private String member_id;       // foreign key (임시)
	private String notice_title;    // 공지사항 제목
	private String notice_content;  // 공지사항 내용
	private Date date;              // 공지사항 생성일자
	private int notice_hit;         // 공지사항 조회수

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNotice_hit() {
		return notice_hit;
	}

	public void setNotice_hit(int notice_hit) {
		this.notice_hit = notice_hit;
	}

}