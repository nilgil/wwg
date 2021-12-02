package com.project.wwg.info.service;

import java.util.List;

import com.project.wwg.info.dto.FoodBoard;

public interface FoodBoardService {
	
	List<FoodBoard> foodlist(FoodBoard foodboard);
		
	int insert(FoodBoard foodboard);
	
	int getTotal(FoodBoard foodboard);
	
	FoodBoard select(int food_no);
	
	void selectUpdate(int food_no);
	
	int update(FoodBoard foodboard);
	
	int delete(int food_no);
	
	int getMaxNum();
	
	int getFood_count();
	
	void updateRe(FoodBoard foodboard);

	void like(int food_no);
	
	//main 페이지에 데이터 호출
	List<FoodBoard> getfood();
}
