package com.project.wwg.info.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.info.dto.FoodBoard;

@Repository
public class FoodBoardDaoImpl implements FoodBoardDao {
	
	@Autowired
	private SqlSessionTemplate session;


	// 총 데이터 갯수
	@Override
	public List<FoodBoard> foodlist(FoodBoard foodboard) {
		return session.selectList("foodMapper.foodlist", foodboard);
	}
	
	// 글갯수
	@Override
	public int getTotal(FoodBoard foodboard) {
		return session.selectOne("foodMapper.getTotal", foodboard);
	}

	// 글작성
	public int insert(FoodBoard foodboard) {
		return session.insert("foodMapper.insert", foodboard);
	}
	
	@Override
	public FoodBoard select(int food_no) {
		return session.selectOne("foodMapper.select", food_no);
	}

	@Override
	public void selectUpdate(int food_no) {
		session.update("foodMapper.selectUpdate", food_no);
	}

	// 글수정
	@Override
	public int update(FoodBoard foodboard) {
		return session.update("foodMapper.update", foodboard);
	}

	// 글삭제
	@Override
	public int delete(int food_no) {
		return session.delete("foodMapper.delete", food_no);
	}

	// 글 번호 증가
	@Override
	public int getMaxNum() {
		return session.selectOne("foodMapper.getMaxNum");
	}

	@Override
	public void updateRe(FoodBoard foodboard) {
		session.update("foodMapper.updateRe", foodboard);
	}

}
