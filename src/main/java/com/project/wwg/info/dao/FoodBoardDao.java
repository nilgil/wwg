package com.project.wwg.info.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.info.dto.FoodBoard;

@Repository
public class FoodBoardDao {
	
	@Autowired
	private SqlSession session;

	// 글작성
	public int insert(FoodBoard foodboard) {
		return session.insert("foodMapper.insert", foodboard);
	}
	
	// 글갯수
	public int getCount() {
		return session.selectOne("count");
	}

	// 총 데이터 갯수
	public List<FoodBoard> getFoodList(int page) {
		return session.selectList("list", page);
	}


}
