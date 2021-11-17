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
	
	void updateRe(FoodBoard foodboard);
}
