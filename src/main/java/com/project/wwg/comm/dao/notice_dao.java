package com.project.wwg.comm.dao;

import java.util.List;

import com.project.wwg.comm.model.notice;

public interface notice_dao {
	// List<notice> list(int startRow, int endRow);
	List<notice> list(notice board);

	int getTotal(notice board);

	int insert(notice board);

	notice select(int num);

	void selectUpdate(int num);

	int update(notice notice);

	int delete(int notice_no);

	int getMaxNum();

	List<notice> getNotice();


}