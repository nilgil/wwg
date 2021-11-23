package com.project.wwg.info.dao;

import java.util.List;
import com.project.wwg.info.dto.ReFoodBoard;

public interface ReFoodBoardDao {
	List<ReFoodBoard> foodlist(int food_no);
	
	void insert(ReFoodBoard ReFoodBoard);
	
	void delete(int food_re_no);
	
	void update(ReFoodBoard ReFoodBoard);
}