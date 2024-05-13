package com.lulu.HeroCorp.service;

import com.lulu.HeroCorp.model.ItemCategory;
import com.lulu.HeroCorp.model.RpgItem;
import com.lulu.HeroCorp.repository.ItemCategoryRepository;
import com.lulu.HeroCorp.repository.RpgItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RpgItemService {
    private final ItemCategoryRepository itemCategoryRepository;
    private final RpgItemRepository rpgItemRepository;

    @Autowired
    public RpgItemService(RpgItemRepository rpgItemRepository, ItemCategoryRepository itemCategoryRepository) {
        this.rpgItemRepository = rpgItemRepository;
        this.itemCategoryRepository = itemCategoryRepository;
    }

    public RpgItem getItemById(Long id){
        Optional<RpgItem> optionalItem = rpgItemRepository.findById(id);
        return optionalItem.orElse(null);
    }

    public long getIdCategoryByName(String name) {
        ItemCategory item = itemCategoryRepository.findByName(name);
        if (item != null) return item.getId();
        return -1;
    }

    public List<RpgItem>  getAllItems(){
        System.out.println("Get all items");
        return rpgItemRepository.findAll();
    }

    public List<RpgItem> getItemsByCategory(String cat){
        System.out.println("Get items by category");
        long id = getIdCategoryByName(cat);
        if (id == -1) return new ArrayList<RpgItem>();
        return rpgItemRepository.findAllByIdCategory(id);
    }

    public List<RpgItem> getItemsByCategoryUnderPrice(String cat, double price){
        System.out.println("Get items by category under price");
        return rpgItemRepository.findAllByIdCategoryAndPriceLessThan(getIdCategoryByName(cat), price);
    }

    public List<RpgItem>getItemsNameContaining(List<String> patterns){
        System.out.println("Get items name containing patterns");
        List<RpgItem> items = new ArrayList<>();
        for(String pattern : patterns){
            items.addAll(rpgItemRepository.findAllByNameContaining(pattern));
        }
        return items;
    }

    public List<RpgItem> getItemsNoEffect(){
        System.out.println("Get items no-effect");
        return rpgItemRepository.findAllByEffect("");
    }

    public RpgItem createItem(String name, String cat, double price, String effect){
        return createItem(name, getIdCategoryByName(cat), price, effect);
    }

    public RpgItem createItem(String name, long cat, double price, String effect){
        System.out.println("Create item");
        RpgItem item = new RpgItem(name,cat,price,effect);
        rpgItemRepository.save(item);
        return item;
    }

    public RpgItem updateRpgItem(RpgItem updatedItem) {
        RpgItem existingItem = rpgItemRepository.findById(updatedItem.getId()).orElse(null);
        if (existingItem == null) {
            return null;
        }

        existingItem.setName(updatedItem.getName());
        existingItem.setIdCategory(updatedItem.getIdCategory());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setEffect(updatedItem.getEffect());

        return rpgItemRepository.save(existingItem);
    }
}
