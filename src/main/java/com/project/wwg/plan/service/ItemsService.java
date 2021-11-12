package com.project.wwg.plan.service;

import com.project.wwg.plan.dao.ItemDao;
import com.project.wwg.plan.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsService {

    private ItemDao itemDao;

    @Autowired
    public ItemsService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public int insertItem(Item item) {

        return 0;
    }

}
