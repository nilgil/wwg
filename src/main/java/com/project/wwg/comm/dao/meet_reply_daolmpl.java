package com.project.wwg.comm.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.comm.model.meet_replydto;


@Repository
public class meet_reply_daolmpl implements meet_reply_dao {
	@Autowired
	private SqlSessionTemplate sst;

	public List<meet_replydto> list(int meet_no) {
		return sst.selectList("mrns.list", meet_no);
	}

	public void insert(meet_replydto mr) {
		sst.insert("mrns.insert", mr);
	}

	public void delete(int meet_re_no) {
		sst.delete("mrns.delete", meet_re_no);
	}

	public void update(meet_replydto mr) {
		sst.update("mrns.update", mr);
	}
}