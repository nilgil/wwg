package com.project.wwg.comm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import board1.dao.ReplyBoardDao;
import com.project.wwg.comm.dao.review_reply_dao;

//import board1.model.ReplyBoard;
import com.project.wwg.comm.model.review_replydto;

@Service
public class review_reply_servicelmpl implements review_reply_service {
	@Autowired
	private review_reply_dao rrd;

	public List<review_replydto> list(int review_no) {
		return rrd.list(review_no);
	}

	public void insert(review_replydto rr) {
		rrd.insert(rr);
	}

	public void delete(int review_re_no) {
		rrd.delete(review_re_no);
	}

	public void update(review_replydto rr) {
		rrd.update(rr);
	}
}