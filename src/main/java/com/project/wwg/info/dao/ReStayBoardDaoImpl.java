package com.project.wwg.info.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.wwg.info.dto.ReStayBoard;

@Repository
public class ReStayBoardDaoImpl implements ReStayBoardDao {
	@Autowired
	private SqlSessionTemplate session;
	
	public List<ReStayBoard> staylist(int rstay_no){
		return session.selectList("restayMapper.staylist", rstay_no);
	}
	
	public void insert(ReStayBoard ReStayBoard) {
		session.insert("restayMapper.insert", ReStayBoard);
	}

	public void delete(int stay_re_no) {
		session.delete("restayMapper.delete", stay_re_no);
	}

	public void update(ReStayBoard ReStayBoard) {
		session.update("restayMapper.update", ReStayBoard);
	}

}
