package com.project.wwg.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.info.dao.TourBoardDao;
import com.project.wwg.info.dto.TourBoard;

@Service
public class TourBoardServiceImpl implements TourBoardService {
	@Autowired
	private TourBoardDao dao;
	
	// 총 데이터 갯수
	@Override
	public List<TourBoard> tourlist(TourBoard tourboard) {
		return dao.tourlist(tourboard);
	}
	
	// 글갯수
	public int getTotal(TourBoard tourBoard) {
		return dao.getTotal(tourBoard);
	}

	// 글작성
	public int insert(TourBoard tourboard) {
		return dao.insert(tourboard);
	}
	
	@Override
	public TourBoard select(int tour_no) {
		return dao.select(tour_no);
	}

	@Override
	public void selectUpdate(int tour_no) {
		dao.selectUpdate(tour_no);
	}
	
	// 글수정
	@Override
	public int update(TourBoard tourboard) {
		return dao.update(tourboard);
	}

	// 글삭제
	@Override
	public int delete(int tour_no) {
		return dao.delete(tour_no);
	}
	
	// 글 번호 증가
	@Override
	public int getMaxNum() {
		return dao.getMaxNum();
	}
	// 게시글 총갯수
	@Override
	public int getTour_count() {
		return dao.getTour_count();
	}
	
	@Override
	public void updateRe(TourBoard tourboard) {
		dao.updateRe(tourboard);
	}

	public void like(int tour_no) {
		dao.like(tour_no);
	}
}
