package com.project.wwg.info.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.info.dto.ReFoodBoard;

@Repository
public class ReFoodBoardDaoImpl implements ReFoodBoardDao {
	@Autowired
	private SqlSessionTemplate session;
	
	public List<ReFoodBoard> foodlist(int rfood_no){
		return session.selectList("refoodMapper.foodlist", rfood_no);
	}
	
	public void insert(ReFoodBoard ReFoodBoard) {
		session.insert("refoodMapper.insert", ReFoodBoard);
	}

	public void delete(int food_re_no) {
		session.delete("refoodMapper.delete", food_re_no);
	}

	public void update(ReFoodBoard ReFoodBoard) {
		session.update("refoodMapper.update", ReFoodBoard);
	}

}
