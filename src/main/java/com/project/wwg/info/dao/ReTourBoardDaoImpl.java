package com.project.wwg.info.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.info.dto.ReTourBoard;

@Repository
public class ReTourBoardDaoImpl implements ReTourBoardDao {
	@Autowired
	private SqlSessionTemplate session;
	
	public List<ReTourBoard> tourlist(int rtour_no){
		return session.selectList("retourMapper.tourlist", rtour_no);
	}
	
	public void insert(ReTourBoard ReTourBoard) {
		session.insert("retourMapper.insert", ReTourBoard);
	}

	public void delete(int tour_re_no) {
		session.delete("retourMapper.delete", tour_re_no);
	}

	public void update(ReTourBoard ReTourBoard) {
		session.update("retourMapper.update", ReTourBoard);
	}

}
