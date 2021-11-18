package com.project.wwg.comm.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.comm.model.notice;


@Repository
public class notice_daolmpl implements notice_dao {
	@Autowired
	private SqlSessionTemplate sst;

//	public List<Board> list(int startRow, int endRow) {
	public List<notice> list(notice board) {
/*		HashMap<String, Integer> hm=new HashMap<String, Integer>();
		hm.put("startRow",startRow);
		hm.put("endRow",endRow);*/
		return sst.selectList("noticens.list",board);
	}
	
	public int getTotal(notice board) {
		return sst.selectOne("noticens.getTotal",board);
	}

	@Override
	public int insert(notice board) {
		return sst.insert("noticens.insert",board);
	}

	@Override
	public notice select(int notice_no) {
		return sst.selectOne("noticens.select", notice_no);
	}

	@Override
	public void selectUpdate(int notice_no) {
		sst.update("noticens.selectUpdate", notice_no);
		
	}

}