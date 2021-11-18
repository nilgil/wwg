package com.project.wwg.qna.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	@Override
	public List<Qna> getQnaList(int page) {
		// TODO Auto-generated method stub
		return st.selectList("qnans.qna_list", page);
	}

	@Override
	public List<Qna> list(Qna qna) {
		// TODO Auto-generated method stub
		return st.selectList("qnans.list", qna);
	}
	

}