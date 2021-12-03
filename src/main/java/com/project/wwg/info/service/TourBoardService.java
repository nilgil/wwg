package com.project.wwg.info.service;

import java.util.List;

import com.project.wwg.info.dto.TourBoard;

public interface TourBoardService {
	
	List<TourBoard> tourlist(TourBoard tourboard);
		
	int insert(TourBoard tourboard);
	
	int getTotal(TourBoard tourboard);
	
	TourBoard select(int tour_no);
	
	void selectUpdate(int tour_no);
	
	int update(TourBoard tourboard);
	
	int delete(int tour_no);
	
	int getMaxNum();
	
	int getTour_count();
	
	void updateRe(TourBoard tourboard);

	void like(int tour_no);
	
	//main 페이지에 데이터 호출
	List<TourBoard> gettour();
}
