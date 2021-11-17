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

}