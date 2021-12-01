package com.project.wwg.comm.dao;

import java.util.List;

import com.project.wwg.comm.model.meet;
import com.project.wwg.comm.model.notice;



public interface meet_dao {
	// List<meet> list(int startRow, int endRow);
	List<meet> list(meet board);

	int getTotal(meet board);

	int insert(meet board);

	meet select(int num);

	void selectUpdate(int num);

	int update(meet meet);

	int delete(int meet_no);

	int getMaxNum();

	void like(int meet_no);
	
	List<meet> getMeet();
}