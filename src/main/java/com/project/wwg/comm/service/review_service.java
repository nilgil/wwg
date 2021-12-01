package com.project.wwg.comm.service;

import java.util.List;

import com.project.wwg.comm.model.review;


public interface review_service {
	// List<review> list(int startRow, int endRow);
	List<review> list(review board);

	int getTotal(review board);

	int insert(review board);


	void selectUpdate(int review_no);

	review select(int review_no);

	int update(review review);

	int delete(int review_no);

	int getMaxNum();

	void like(int review_no);
	
	List<review> getReview1();
	
	List<review> getReview2();
}