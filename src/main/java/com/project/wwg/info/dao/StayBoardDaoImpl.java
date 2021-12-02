package com.project.wwg.info.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.info.dto.StayBoard;

@Repository
public class StayBoardDaoImpl implements StayBoardDao {
	
	@Autowired
	private SqlSessionTemplate session;


	// 총 데이터 갯수
	@Override
	public List<StayBoard> staylist(StayBoard stayboard) {
		return session.selectList("stayMapper.staylist", stayboard);
	}
	
	// 글갯수
	@Override
	public int getTotal(StayBoard stayboard) {
		return session.selectOne("stayMapper.getTotal", stayboard);
	}

	// 글작성
	public int insert(StayBoard stayboard) {
		return session.insert("stayMapper.insert", stayboard);
	}
	
	@Override
	public StayBoard select(int stay_no) {
		return session.selectOne("stayMapper.select", stay_no);
	}

	@Override
	public void selectUpdate(int stay_no) {
		session.update("stayMapper.selectUpdate", stay_no);
	}

	// 글수정
	@Override
	public int update(StayBoard stayboard) {
		return session.update("stayMapper.update", stayboard);
	}

	// 글삭제
	@Override
	public int delete(int stay_no) {
		return session.delete("stayMapper.delete", stay_no);
	}

	// 글 번호 증가
	@Override
	public int getMaxNum() {
		return session.selectOne("stayMapper.getMaxNum");
	}
	// 게시글 총갯수
	@Override
	public int getStay_count() {
		return session.selectOne("stayMapper.stay_count");
	}

	@Override
	public void updateRe(StayBoard stayboard) {
		session.update("stayMapper.updateRe", stayboard);
	}
	
	@Override
	public void like(int stay_no) {
		session.update("stayMapper.like", stay_no);
	}

	//main 페이지에 데이터 호출
	@Override
	public List<StayBoard> getstay() {
		// TODO Auto-generated method stub
		return session.selectList("stayMapper.stay");
	}

}
