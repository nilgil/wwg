package com.project.wwg.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.info.dao.FoodBoardDao;
import com.project.wwg.info.dto.FoodBoard;

@Service
public class FoodBoardServiceImpl implements FoodBoardService {
	@Autowired
	private FoodBoardDao dao;
	
	// 총 데이터 갯수
	@Override
	public List<FoodBoard> foodlist(FoodBoard foodboard) {
		return dao.foodlist(foodboard);
	}
	
	// 글갯수
	public int getTotal(FoodBoard foodBoard) {
		return dao.getTotal(foodBoard);
	}

	// 글작성
	public int insert(FoodBoard foodboard) {
		return dao.insert(foodboard);
	}
	
	@Override
	public FoodBoard select(int food_no) {
		return dao.select(food_no);
	}

	@Override
	public void selectUpdate(int food_no) {
		dao.selectUpdate(food_no);
	}
	
	// 글수정
	@Override
	public int update(FoodBoard foodboard) {
		return dao.update(foodboard);
	}

	// 글삭제
	@Override
	public int delete(int food_no) {
		return dao.delete(food_no);
	}
	
	// 글 번호 증가
	@Override
	public int getMaxNum() {
		return dao.getMaxNum();
	}

	@Override
	public void updateRe(FoodBoard foodboard) {
		dao.updateRe(foodboard);
	}

}
