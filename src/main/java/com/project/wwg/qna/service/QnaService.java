package com.project.wwg.qna.service;

import java.util.List;

import com.project.wwg.qna.model.Qna;

public interface QnaService{
	
	List<Qna> list(Qna qna);
	
	int insert(Qna qna);
	
	Qna select(int qna_no);
	
	int update(Qna qna);
	
	int delete(int qna_no);
	
	int getMaxNum();

	int getListCount();

	List<Qna> getPageList(int page);


}