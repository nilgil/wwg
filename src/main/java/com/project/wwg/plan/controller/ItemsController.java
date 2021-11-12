package com.project.wwg.plan.controller;

import com.project.wwg.plan.dto.Item;
import com.project.wwg.plan.service.ItemsResetFromApiService;
import com.project.wwg.plan.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 아이템 컨트롤러
 *
 * @author giri
 */
@Controller
@RequestMapping("/items/*")
public class ItemsController {

    private ItemsResetFromApiService itemsResetFromApiService;
    private ItemsService itemsService;

    @Autowired
    public ItemsController(ItemsResetFromApiService itemsResetFromApiService, ItemsService itemsService) {
        this.itemsResetFromApiService = itemsResetFromApiService;
        this.itemsService = itemsService;
    }

    @GetMapping("insert")
    public String itemInsertForm() {
        return "/plan/itemInsertForm";
    }

    @PostMapping("insert")
    public String itemInsert(@RequestBody Item item) {
        itemsService.insertItem(item);
        return "/plan/itemInsertSuccess";
    }

    @PostMapping("reset")
    public String itemsResetFromApi() {
        int result = itemsResetFromApiService.itemsUpdateFromApi();
        System.out.println("Items Reset From API Result = " + result);
        return "/plan/itemUpdateFromApiSuccess";
    }

}
