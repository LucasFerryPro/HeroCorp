package com.lulu.HeroCorp.service;

import com.lulu.HeroCorp.model.ItemCategory;
import com.lulu.HeroCorp.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryService {
    private final ItemCategoryRepository itemCategoryRepository;

    @Autowired
    public ItemCategoryService(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    public List<ItemCategory> getAllCategories() {
        System.out.println("Getting all categories");
        return itemCategoryRepository.findAll();
    }

    public ItemCategory createCategory(String name) {
        System.out.println("Creating category");
        if (itemCategoryRepository.findByName(name) != null) return null;
        ItemCategory category = new ItemCategory(name);
        itemCategoryRepository.save(category);
        return category;
    }

    public ItemCategory getCategory(String name) {
        System.out.println("Getting category");
        return itemCategoryRepository.findByName(name);
    }
}
