package com.project.wwg.comm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.comm.dao.notice_dao;
import com.project.wwg.comm.model.notice;


@Service
public class notice_servicelmpl implements notice_service {
	@Autowired
	private notice_dao nd;
	
	// public List<notice> list(int startRow, int endRow) {
		public List<notice> list(notice board) {
			return nd.list(board);
		//	return nd.list(startRow, endRow);
		}
	
	public int getTotal(notice board) {
		return nd.getTotal(board);
	}

	@Override
	public int insert(notice board) {
		return nd.insert(board);
	}

	@Override
	public notice select(int notice_no) {
		return nd.select(notice_no);
	}

	@Override
	public void selectUpdate(int notice_no) {
		nd.selectUpdate(notice_no);
		
	}

	@Override
	public int update(notice notice) {
		return nd.update(notice);
	}

	@Override
	public int delete(int notice_no) {
		return nd.delete(notice_no);
	}

	@Override
	public int getMaxNum() {
		return nd.getMaxNum();
	}

	@Override
	public List<notice> getNotice() {
		// TODO Auto-generated method stub
		return nd.getNotice();
	}

}