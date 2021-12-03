package com.project.wwg.info.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.info.dto.TourBoard;

@Repository
public class TourBoardDaoImpl implements TourBoardDao {
	
	@Autowired
	private SqlSessionTemplate session;


	// 총 데이터 갯수
	@Override
	public List<TourBoard> tourlist(TourBoard tourboard) {
		return session.selectList("tourMapper.tourlist", tourboard);
	}
	
	// 글갯수
	@Override
	public int getTotal(TourBoard tourboard) {
		return session.selectOne("tourMapper.getTotal", tourboard);
	}

	// 글작성
	public int insert(TourBoard tourboard) {
		return session.insert("tourMapper.insert", tourboard);
	}
	
	@Override
	public TourBoard select(int tour_no) {
		return session.selectOne("tourMapper.select", tour_no);
	}

	@Override
	public void selectUpdate(int tour_no) {
		session.update("tourMapper.selectUpdate", tour_no);
	}

	// 글수정
	@Override
	public int update(TourBoard tourboard) {
		return session.update("tourMapper.update", tourboard);
	}

	// 글삭제
	@Override
	public int delete(int tour_no) {
		return session.delete("tourMapper.delete", tour_no);
	}

	// 글 번호 증가
	@Override
	public int getMaxNum() {
		return session.selectOne("tourMapper.getMaxNum");
	}
	// 게시글 총갯수
	@Override
	public int getTour_count() {
		return session.selectOne("tourMapper.tour_count");
	}

	@Override
	public void updateRe(TourBoard tourboard) {
		session.update("tourMapper.updateRe", tourboard);
	}
	
	@Override
	public void like(int tour_no) {
		session.update("tourMapper.like", tour_no);
	}

	//main 페이지에 데이터 호출
	@Override
	public List<TourBoard> gettour() {
		// TODO Auto-generated method stub
		return session.selectList("tourMapper.tour");
	}

}
