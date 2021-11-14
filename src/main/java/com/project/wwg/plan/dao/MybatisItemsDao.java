package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.Item;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MybatisItemsDao implements ItemsDao {

    private SqlSession sqlSession;

    @Autowired
    public MybatisItemsDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Item> searchItems(String keyword) {
        return sqlSession.selectList("items.searchItem", keyword);
    }

    @Override
    public int insertItem(Item item) {
        return 0;
    }

    @Override
    public int insertItems(List<Item> items) {
        return 0;
    }

    @Override
    public int deleteAllItems() {
        return 0;
    }
}
