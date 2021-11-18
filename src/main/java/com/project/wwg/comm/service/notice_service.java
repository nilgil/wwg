package com.project.wwg.comm.service;

import java.util.List;

import com.project.wwg.comm.model.notice;


public interface notice_service {
	// List<notice> list(int startRow, int endRow);
	List<notice> list(notice board);

	int getTotal(notice board);

	int insert(notice board);


	void selectUpdate(int notice_no);

	notice select(int notice_no);

	
}