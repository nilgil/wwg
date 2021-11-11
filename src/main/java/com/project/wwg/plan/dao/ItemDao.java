package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 아이템 DAO
 * @author giri
 */
@Repository
public interface ItemDao {

    Item getItem(String category,int idx);

    void insertItem(Item item);

    void updateItem(Item item);

    void deleteItem(Item item);

    List<Item> getSearchItems(String category, String keyword);

    int insertItems(List<Item> items);
}
