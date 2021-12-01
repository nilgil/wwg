package com.project.wwg.comm.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.comm.model.meet;



@Repository
public class meet_daolmpl implements meet_dao {
	@Autowired
	private SqlSessionTemplate sst;

//	public List<Board> list(int startRow, int endRow) {
	public List<meet> list(meet meet) {
/*		HashMap<String, Integer> hm=new HashMap<String, Integer>();
		hm.put("startRow",startRow);
		hm.put("endRow",endRow);*/
		return sst.selectList("meetns.list",meet);
	}
	
	public int getTotal(meet meet) {
		return sst.selectOne("meetns.getTotal",meet);
	}

	@Override
	public int insert(meet meet) {
		return sst.insert("meetns.insert",meet);
	}

	@Override
	public meet select(int meet_no) {
		return sst.selectOne("meetns.select", meet_no);
	}

	@Override
	public void selectUpdate(int meet_no) {
		sst.update("meetns.selectUpdate", meet_no);
		
	}

	@Override
	public int update(meet meet) {
		return sst.update("meetns.update", meet);
	}

	@Override
	public int delete(int meet_no) {
		return sst.update("meetns.delete", meet_no);
	}

	@Override
	public int getMaxNum() {
		return sst.selectOne("meetns.getMaxNum");
	}
	@Override
	public void like(int meet_no) {
		sst.update("meetns.like", meet_no);
	}

	@Override
	public List<meet> getMeet() {
		// TODO Auto-generated method stub
		return sst.selectList("meetns.meet");
	}

}