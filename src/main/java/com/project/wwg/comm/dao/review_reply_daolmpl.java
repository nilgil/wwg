package com.project.wwg.comm.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.comm.model.review_reply;


@Repository
public class review_reply_daolmpl implements review_reply_dao {
	@Autowired
	private SqlSessionTemplate sst;

	public List<review_reply> list(int review_no) {
		return sst.selectList("rrns.list", review_no);
	}

	public void insert(review_reply rr) {
		sst.insert("rrns.insert", rr);
	}

	public void delete(int review_re_no) {
		sst.delete("rrns.delete", review_re_no);
	}

	public void update(review_reply rr) {
		sst.update("rrns.update", rr);
	}
}