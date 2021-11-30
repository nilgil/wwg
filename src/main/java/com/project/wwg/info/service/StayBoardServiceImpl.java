package com.project.wwg.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.info.dao.StayBoardDao;
import com.project.wwg.info.dto.StayBoard;

@Service
public class StayBoardServiceImpl implements StayBoardService {
	@Autowired
	private StayBoardDao dao;
	
	// 총 데이터 갯수
	@Override
	public List<StayBoard> staylist(StayBoard stayboard) {
		return dao.staylist(stayboard);
	}
	
	// 글갯수
	public int getTotal(StayBoard stayBoard) {
		return dao.getTotal(stayBoard);
	}

	// 글작성
	public int insert(StayBoard stayboard) {
		return dao.insert(stayboard);
	}
	
	@Override
	public StayBoard select(int stay_no) {
		return dao.select(stay_no);
	}

	@Override
	public void selectUpdate(int stay_no) {
		dao.selectUpdate(stay_no);
	}
	
	// 글수정
	@Override
	public int update(StayBoard stayboard) {
		return dao.update(stayboard);
	}

	// 글삭제
	@Override
	public int delete(int stay_no) {
		return dao.delete(stay_no);
	}
	
	// 글 번호 증가
	@Override
	public int getMaxNum() {
		return dao.getMaxNum();
	}
	
	@Override
	public void updateRe(StayBoard stayboard) {
		dao.updateRe(stayboard);
	}

	public void like(int stay_no) {
		dao.like(stay_no);
	}
}
