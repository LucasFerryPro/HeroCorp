package com.lulu.HeroCorp.control;


import com.lulu.HeroCorp.model.ItemCategory;
import com.lulu.HeroCorp.service.ItemCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rpg/categories")
public class ItemCategoryController {

    private final ItemCategoryService itemCategoryService;

    public ItemCategoryController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ItemCategory>> getAllCategories() {
        List<ItemCategory> categories = itemCategoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/{name}")
    public ResponseEntity<ItemCategory> createCategory(@PathVariable String name) {
        ItemCategory category = itemCategoryService.createCategory(name);
        if (category == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}

