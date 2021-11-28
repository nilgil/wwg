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
	
	public List<Qna> list(Qna qna){ 
		return qd.list(qna);
	}
    
	//게시글작성
	@Override
	public int insert(Qna qna) {
		// TODO Auto-generated method stub
		return qd.insert(qna);
	}

	//상세페이지
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
	
	//seq대신
	public int getMaxNum() {
		return qd.getMaxNum();
	}

	@Override
	public int getListCount() {
		// TODO Auto-generated method stub
		return qd.getListCount();
	}

	//게시글목록
	@Override
	public List<Qna> getPageList(Qna qna) {		
		return qd.getQnaList(qna);
	}

	//조회수
	@Override
	public void hitupdate(int qna_no) {
		// TODO Auto-generated method stub
		qd.hitupdate(qna_no);
	}

	
	@Override
	public void insertCom(Qna qna) {
		// TODO Auto-generated method stub
		System.out.println("insertCom service까지옴");
		
		//seq 1 증가
		qd.refEdit(qna);
		
		System.out.println("insertCom refEdit까지옴");
		
		//
		qna.setQna_re_lev(qna.getQna_re_lev()+1); //부모보다 1증가
		qna.setQna_re_seq(qna.getQna_re_seq()+1); //seq증가
		System.out.println("insertCom lev, seq증가");
		
		//
		qd.qnaCommentOk(qna);
		System.out.println("insertCom qnaCommentOK까지옴");
	}

	@Override
	public int getTotal(Qna qna) {
		// TODO Auto-generated method stub
		return qd.getTotal(qna);
	}
	
	
	public int getComment(Qna qna) {
		// TODO Auto-generated method stub
		return qd.getComCount(qna);
	}

	@Override
	public List<Qna> getQnaMain() {
		// TODO Auto-generated method stub
		return qd.getQnaMain();
	}
	



	
}