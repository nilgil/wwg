package com.project.wwg.comm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.comm.dao.review_dao;
import com.project.wwg.comm.model.review;



@Service
public class review_servicelmpl implements review_service {
	@Autowired
	private review_dao rd;
	
	// public List<review> list(int startRow, int endRow) {
		public List<review> list(review board) {
			return rd.list(board);
		//	return rd.list(startRow, endRow);
		}
	
	public int getTotal(review board) {
		return rd.getTotal(board);
	}

	@Override
	public int insert(review board) {
		return rd.insert(board);
	}

	@Override
	public review select(int review_no) {
		return rd.select(review_no);
	}

	@Override
	public void selectUpdate(int review_no) {
		rd.selectUpdate(review_no);
		
	}

	@Override
	public int update(review review) {
		return rd.update(review);
	}

	@Override
	public int delete(int review_no) {
		return rd.delete(review_no);
	}

	@Override
	public int getMaxNum() {
		return rd.getMaxNum();
	}
	
	public void like(int review_no) {
		rd.like(review_no);
	}
}