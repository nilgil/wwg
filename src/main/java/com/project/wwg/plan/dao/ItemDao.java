package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 아이템 DAO
 * @author giri
 */
public interface ItemDao {
    int insertItem(Item item);

    int insertItems(List<Item> items);

    int deleteAllItems();
}
