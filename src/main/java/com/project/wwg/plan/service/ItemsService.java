package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.ItemsDao;
import com.project.wwg.plan.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {

    private ItemsDao itemsDao;

    @Autowired
    public ItemsService(ItemsDao itemsDao) {
        this.itemsDao = itemsDao;
    }

    public List<Item> searchItem(String keyword) {
        return itemsDao.searchItems(keyword);
    }

}
