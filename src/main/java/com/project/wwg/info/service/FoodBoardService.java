package com.project.wwg.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.info.dao.FoodBoardDao;
import com.project.wwg.info.dto.FoodBoard;

@Service
public class FoodBoardService {
	
	@Autowired
	private FoodBoardDao dao;

	// 글작성
	public int insert(FoodBoard foodboard) {
		return dao.insert(foodboard);
	}

	// 글갯수
	public int getCount() {
		return dao.getCount();
	}

	// 총 데이터 갯수
	public List<FoodBoard> getFoodList(int page) {
		return dao.getFoodList(page);
	}


}
