package com.project.wwg.info.dao;

import java.util.List;
import com.project.wwg.info.dto.ReStayBoard;

public interface ReStayBoardDao {
	List<ReStayBoard> staylist(int stay_no);
	
	void insert(ReStayBoard ReStayBoard);
	
	void delete(int stay_re_no);
	
	void update(ReStayBoard ReStayBoard);
}