package com.project.wwg.info.dao;

import java.util.List;
import com.project.wwg.info.dto.ReTourBoard;

public interface ReTourBoardDao {
	List<ReTourBoard> tourlist(int tour_no);
	
	void insert(ReTourBoard ReTourBoard);
	
	void delete(int tour_re_no);
	
	void update(ReTourBoard ReTourBoard);
}