package com.project.wwg.info.service;

import java.util.List;
import com.project.wwg.info.dto.ReFoodBoard;

public interface ReFoodBoardService {
	List<ReFoodBoard> foodlist(int food_no);
	
	void insert(ReFoodBoard ReFoodBoard);
	
	void delete(int food_re_no);
	
	void update(ReFoodBoard ReFoodBoard);

}
