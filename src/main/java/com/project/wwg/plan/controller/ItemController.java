package com.project.wwg.plan.controller;

import com.project.wwg.plan.dto.Item;
import com.project.wwg.plan.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 아이템 컨트롤러
 * @author giri
 */
@RestController
@RequestMapping("/item/*")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/all")
    public List<Item> getAllItems() {

    }

    @GetMapping("/{category}/{idx}")
    public Item getItem(@PathVariable String category, @PathVariable int idx) {
        return itemService.getItem(category, idx);
    }

    @PostMapping("/insert")
    public void insertItem(Item item) {
        itemService.insertItem(item);
    }

    @PutMapping("/update")
    public void updateItem(Item item) {
        itemService.updateItem(item);
    }

    @DeleteMapping("/delete")
    public void deleteItem(Item item) {
        itemService.deleteItem(item);
    }

    @GetMapping("/{category}/{keyword}")
    public List<Item> getSearchItems(@PathVariable String category, @PathVariable String keyword) {
        return itemService.getSearchItems(category, keyword);
    }

    @PostMapping("")
    public int insertItems(List<Item> items) {
        return itemService.insertItems(items);
    }
}
