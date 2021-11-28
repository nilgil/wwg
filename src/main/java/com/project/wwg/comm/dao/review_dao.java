package com.project.wwg.comm.dao;

import java.util.List;

import com.project.wwg.comm.model.review;


public interface review_dao {
	// List<review> list(int startRow, int endRow);
	List<review> list(review board);

	int getTotal(review board);

	int insert(review board);

	review select(int num);

	void selectUpdate(int num);

	int update(review review);

	int delete(int review_no);

	int getMaxNum();

	void like(int review_no);
}