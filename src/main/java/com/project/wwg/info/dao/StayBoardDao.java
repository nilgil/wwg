package com.project.wwg.info.dao;

import java.util.List;

import com.project.wwg.info.dto.StayBoard;

public interface StayBoardDao {
	
	List<StayBoard> staylist(StayBoard stayboard);
		
	int insert(StayBoard stayboard);
	
	int getTotal(StayBoard stayboard);
	
	StayBoard select(int stay_no);
	
	void selectUpdate(int stay_no);
	
	int update(StayBoard stayboard);
	
	int delete(int stay_no);
	
	int getMaxNum();
	
	int getStay_count();
	
	void updateRe(StayBoard stayboard);

	void like(int stay_no);

	//main 페이지에 데이터 호출
	List<StayBoard> getstay();
}
