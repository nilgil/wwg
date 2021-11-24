package com.project.wwg.comm.dao;

import java.util.List;

import com.project.wwg.comm.model.review_replydto;

public interface review_reply_dao {
	List<review_replydto> list(int review_no);

	void insert(review_replydto rr);

	void delete(int review_re_no);

	void update(review_replydto rr);
}