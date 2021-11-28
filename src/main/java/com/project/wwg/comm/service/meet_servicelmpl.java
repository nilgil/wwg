package com.project.wwg.comm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.comm.dao.meet_dao;
import com.project.wwg.comm.model.meet;




@Service
public class meet_servicelmpl implements meet_service {
	@Autowired
	private meet_dao md;
	
	// public List<meet> list(int startRow, int endRow) {
		public List<meet> list(meet board) {
			return md.list(board);
		//	return rd.list(startRow, endRow);
		}
	
	public int getTotal(meet board) {
		return md.getTotal(board);
	}

	@Override
	public int insert(meet board) {
		return md.insert(board);
	}

	@Override
	public meet select(int meet_no) {
		return md.select(meet_no);
	}

	@Override
	public void selectUpdate(int meet_no) {
		md.selectUpdate(meet_no);
		
	}

	@Override
	public int update(meet meet) {
		return md.update(meet);
	}

	@Override
	public int delete(int meet_no) {
		return md.delete(meet_no);
	}

	@Override
	public int getMaxNum() {
		return md.getMaxNum();
	}
	
	public void like(int meet_no) {
		md.like(meet_no);
	}
}