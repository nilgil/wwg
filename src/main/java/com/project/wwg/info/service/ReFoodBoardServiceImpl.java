package com.project.wwg.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.info.dao.ReFoodBoardDao;
import com.project.wwg.info.dto.ReFoodBoard;

@Service
public class ReFoodBoardServiceImpl implements ReFoodBoardService {
	@Autowired
	private ReFoodBoardDao rbd;
	
	public List<ReFoodBoard> foodlist(int food_no) {
		return rbd.foodlist(food_no);
	}
	
	public void insert(ReFoodBoard ReFoodBoard) {
		rbd.insert(ReFoodBoard);
	}

	public void delete(int food_re_no) {
		rbd.delete(food_re_no);
	}

	public void update(ReFoodBoard ReFoodBoard) {
		rbd.update(ReFoodBoard);
	}
}
