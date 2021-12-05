package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.PageInfo;
import com.project.wwg.plan.dto.Spot;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MybatisSpotDao implements SpotDao {

    private SqlSession sqlSession;

    @Autowired
    public MybatisSpotDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // ------------------------------------ [C] ------------------------------------
    @Override
    public int insertSpot(Spot spot) {
        return sqlSession.insert("spot.insertSpot", spot);
    }

    @Override
    public int insertSpots(List<Spot> spots) {
        return sqlSession.insert("spot.insertSpots", spots);
    }

    // ------------------------------------ [R] ------------------------------------
    @Override
    public Spot searchSpotOne(String title){
        return sqlSession.selectOne("spot.searchSpotOne", title);
    }

    @Override
    public List<Spot> searchSpots(PageInfo pageInfo) {
        return sqlSession.selectList("spot.searchSpots", pageInfo);
    }

    @Override
    public int getSearchSpotsCount(String keyword) {
        return sqlSession.selectOne("spot.getSearchSpotsCount", keyword);
    }

    // ------------------------------------ [U] ------------------------------------

    // ------------------------------------ [D] ------------------------------------
    @Override
    public int deleteAllSpots() {
        return sqlSession.delete("spot.deleteAllSpots");
    }
}
