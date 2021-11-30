package com.project.wwg.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.info.dao.ReStayBoardDao;
import com.project.wwg.info.dto.ReStayBoard;

@Service
public class ReStayBoardServiceImpl implements ReStayBoardService {
	@Autowired
	private ReStayBoardDao rbd;
	
	public List<ReStayBoard> staylist(int stay_no) {
		return rbd.staylist(stay_no);
	}
	
	public void insert(ReStayBoard ReStayBoard) {
		rbd.insert(ReStayBoard);
	}

	public void delete(int stay_re_no) {
		rbd.delete(stay_re_no);
	}

	public void update(ReStayBoard ReStayBoard) {
		rbd.update(ReStayBoard);
	}
}
