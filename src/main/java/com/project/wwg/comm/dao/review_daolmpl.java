package com.project.wwg.comm.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.comm.model.review;



@Repository
public class review_daolmpl implements review_dao {
	@Autowired
	private SqlSessionTemplate sst;

//	public List<Board> list(int startRow, int endRow) {
	public List<review> list(review review) {
/*		HashMap<String, Integer> hm=new HashMap<String, Integer>();
		hm.put("startRow",startRow);
		hm.put("endRow",endRow);*/
		return sst.selectList("reviewns.list",review);
	}
	
	public int getTotal(review review) {
		return sst.selectOne("reviewns.getTotal",review);
	}

	@Override
	public int insert(review review) {
		return sst.insert("reviewns.insert",review);
	}

	@Override
	public review select(int review_no) {
		return sst.selectOne("reviewns.select", review_no);
	}

	@Override
	public void selectUpdate(int review_no) {
		sst.update("reviewns.selectUpdate", review_no);
		
	}

	@Override
	public int update(review review) {
		return sst.update("reviewns.update", review);
	}

	@Override
	public int delete(int review_no) {
		return sst.update("reviewns.delete", review_no);
	}

	@Override
	public int getMaxNum() {
		return sst.selectOne("reviewns.getMaxNum");
	}
	@Override
	public void like(int review_no) {
		sst.update("reviewns.like", review_no);
	}

	@Override
	public List<review> getReview1() {
		// TODO Auto-generated method stub
		return sst.selectList("reviewns.review1");
	}
	
	@Override
	public List<review> getReview2() {
		// TODO Auto-generated method stub
		return sst.selectList("reviewns.review2");
	}

}