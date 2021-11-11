package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.ItemDao;
import com.project.wwg.plan.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 아이템 서비스
 *
 * @author giri
 */
@Service
public class ItemService {

    private ItemDao itemDao;

    @Autowired
    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public Item getItem(String category, int idx) {
        return itemDao.getItem(category, idx);
    }

    public void insertItem(Item item) {
        itemDao.insertItem(item);
    }

    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    public void deleteItem(Item item) {
        itemDao.deleteItem(item);
    }

    public List<Item> getSearchItems(String category, String keyword) {
        return itemDao.getSearchItems(category, keyword);
    }

    public int insertItems(List<Item> items) {
        return itemDao.insertItems(items);
    }
}
