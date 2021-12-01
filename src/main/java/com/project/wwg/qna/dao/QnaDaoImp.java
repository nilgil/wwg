package com.project.wwg.qna.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.comm.dao.notice_dao;
import com.project.wwg.comm.model.notice;
import com.project.wwg.qna.model.Qna;


@Repository
public class QnaDaoImp implements QnaDao{
	
	@Autowired
	private SqlSessionTemplate st;
	
	//글작성
	public int insert(Qna qna) {
		// TODO Auto-generated method stub
		return st.insert("qnans.insert", qna);
		// st.sqlDML("xml네임.id값",매개변수)
	}

	//상세페이지
	public Qna select(int qna_no) {
		// TODO Auto-generated method stub
		return st.selectOne("qnans.select", qna_no);
	}
    

	public int update(Qna qna) {
		// TODO Auto-generated method stub
		return st.update("qnans.update", qna);
	}
    

	public int delete(int qna_no) {
		// TODO Auto-generated method stub
		return st.delete("qnans.delete", qna_no);
	}

	//seq대신사용
	@Override
	public int getMaxNum() {
		// TODO Auto-generated method stub
		return st.selectOne("qnans.getMaxNum");
	}

	//게시판 총 게시물 개수
	@Override
	public int getListCount() {
		// TODO Auto-generated method stub
		return st.selectOne("qnans.qna_count");
	}
	
    //list목록
	@Override
	public List<Qna> getQnaList(Qna qna) {
		// TODO Auto-generated method stub
		return st.selectList("qnans.qna_list", qna);
	}

	@Override
	public List<Qna> list(Qna qna) {
		// TODO Auto-generated method stub
		return st.selectList("qnans.list", qna);
	}

	//조회수
	@Override
	public void hitupdate(int qna_no) {
		// TODO Auto-generated method stub
		st.update("qnans.hitupdate", qna_no);
	}

	//답글lev증가
	@Override
	public void refEdit(Qna qna) {
		// TODO Auto-generated method stub
		st.update("qnans.qna_lev", qna);
	}

	//답글달기
	@Override
	public void qnaCommentOk(Qna qna) {
		// TODO Auto-generated method stub
		st.insert("qnans.insertCom", qna);
	}

	@Override
	public int getTotal(Qna qna) {
		// TODO Auto-generated method stub
		return st.selectOne("qnans.getTotal", qna);
	}

	@Override
	public int getComCount(Qna qna) {
		// TODO Auto-generated method stub
		return st.selectOne("qnans.ComCount", qna);
	}

	@Override
	public List<Qna> getQnaMain() {
		// TODO Auto-generated method stub
		return st.selectList("qnans.qnaMain");
	}

	
	

}