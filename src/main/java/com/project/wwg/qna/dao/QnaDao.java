package com.project.wwg.qna.dao;

import java.util.List;

import com.project.wwg.qna.model.Qna;

public interface QnaDao{
	List<Qna> list(Qna qna);

	int insert(Qna qna);

	Qna select(int qna_no);

	int update(Qna qna);

	int delete(int qna_no);

	int getMaxNum();

	int getListCount();

	List<Qna> getQnaList(Qna qna);

	void hitupdate(int qna_no);
	
	void refEdit(Qna qna);

	void qnaCommentOk(Qna qna);

	int getTotal(Qna qna);

	
}