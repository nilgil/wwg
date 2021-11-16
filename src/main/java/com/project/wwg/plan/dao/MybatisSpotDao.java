package com.project.wwg.plan.dao;

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

    @Override
    public List<Spot> getSpotsList(int startRow, int endRow) {

        return null;
    }

    /**
     * 검색어로 Spot 검색하여 리스트 반환
     *
     * @param keyword
     * @return
     */
    @Override
    public List<Spot> searchSpots(String keyword) {
        return sqlSession.selectList("spot.searchSpot", keyword);
    }

    /**
     * Spot 1개 insert
     *
     * @param item
     * @return
     */
    @Override
    public void insertSpot(Spot item) {
        sqlSession.insert("spot.insertSpot", item);
    }

    /**
     * Spot 여러 개 insert
     *
     * @param items
     * @return
     */
    @Override
    public int insertSpots(List<Spot> items) {
        return sqlSession.insert("spot.insertSpots", items);
    }

    /**
     * id로 Spot 삭제
     * @param id
     */
    @Override
    public void deleteSpot(String id) {
        sqlSession.delete("spot.deleteSpot", id);
    }

    /**
     * 모든 Spot 삭제
     *
     * @return
     */
    @Override
    public int deleteAllSpots() {
        return sqlSession.delete("spot.deleteAllSpots");
    }
}
