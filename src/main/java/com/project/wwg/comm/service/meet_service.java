package com.project.wwg.comm.service;

import java.util.List;

import com.project.wwg.comm.model.meet;



public interface meet_service {
	// List<meet> list(int startRow, int endRow);
	List<meet> list(meet board);

	int getTotal(meet board);

	int insert(meet board);


	void selectUpdate(int meet_no);

	meet select(int meet_no);

	int update(meet meet);

	int delete(int meet_no);

	int getMaxNum();

	void like(int meet_no);
}