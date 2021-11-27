package com.project.wwg.comm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import board1.dao.ReplyBoardDao;
import com.project.wwg.comm.dao.meet_reply_dao;

//import board1.model.ReplyBoard;
import com.project.wwg.comm.model.meet_replydto;

@Service
public class meet_reply_servicelmpl implements meet_reply_service {
	@Autowired
	private meet_reply_dao mrd;

	public List<meet_replydto> list(int meet_no) {
		return mrd.list(meet_no);
	}

	public void insert(meet_replydto mr) {
		mrd.insert(mr);
	}

	public void delete(int meet_re_no) {
		mrd.delete(meet_re_no);
	}

	public void update(meet_replydto mr) {
		mrd.update(mr);
	}
}