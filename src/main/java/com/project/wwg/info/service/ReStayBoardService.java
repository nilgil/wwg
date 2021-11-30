package com.project.wwg.info.service;

import java.util.List;
import com.project.wwg.info.dto.ReStayBoard;

public interface ReStayBoardService {
	List<ReStayBoard> staylist(int stay_no);
	
	void insert(ReStayBoard ReStayBoard);
	
	void delete(int stay_re_no);
	
	void update(ReStayBoard ReStayBoard);

}
