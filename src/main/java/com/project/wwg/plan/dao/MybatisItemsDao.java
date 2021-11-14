package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.Item;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MybatisItemsDao implements ItemsDao {

    private SqlSessionTemplate sqlSession;

    @Autowired
    public MybatisItemsDao(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public int insertItem(Item item) {
        return 0;
    }

    @Override
    public int insertItems(List<Item> items) {
        return sqlSession.insert("com.project.wwg.plan.dao.MybatisItemDao.insertItems", items);
    }

    @Override
    public int deleteAllItems() {
        return sqlSession.delete("com.project.wwg.plan.dao.MybatisItemDao.deleteAllItems");
    }

}
