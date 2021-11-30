package com.project.wwg.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.info.dao.ReTourBoardDao;
import com.project.wwg.info.dto.ReTourBoard;

@Service
public class ReTourBoardServiceImpl implements ReTourBoardService {
	@Autowired
	private ReTourBoardDao rbd;
	
	public List<ReTourBoard> tourlist(int tour_no) {
		return rbd.tourlist(tour_no);
	}
	
	public void insert(ReTourBoard ReTourBoard) {
		rbd.insert(ReTourBoard);
	}

	public void delete(int tour_re_no) {
		rbd.delete(tour_re_no);
	}

	public void update(ReTourBoard ReTourBoard) {
		rbd.update(ReTourBoard);
	}
}
