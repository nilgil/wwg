package com.project.wwg.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.qna.dao.QnaDao;
import com.project.wwg.qna.model.Qna;

@Service
public class QnaServiceImp implements QnaService{
	
	@Autowired
	private QnaDao qd;
	
	public List<Qna> list(){
		return qd.list();
	}

	@Override
	public int insert(Qna qna) {
		// TODO Auto-generated method stub
		return qd.insert(qna);
	}

	@Override
	public Qna select(int qna_no) {
		// TODO Auto-generated method stub
		return qd.select(qna_no);
	}

	@Override
	public int update(Qna qna) {
		// TODO Auto-generated method stub
		return qd.update(qna);
	}

	@Override
	public int delete(int qna_no) {
		// TODO Auto-generated method stub
		return qd.delete(qna_no);
	}
	
	public int getMaxNum() {
		return qd.getMaxNum();
	}
	
}