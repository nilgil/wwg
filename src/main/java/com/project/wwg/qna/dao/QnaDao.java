package com.project.wwg.qna.dao;

import java.util.List;

import com.project.wwg.qna.model.Qna;

public interface QnaDao{
	List<Qna> list();

	int insert(Qna qna);

	Qna select(int qna_no);

	int update(Qna qna);

	int delete(int qna_no);
	
}