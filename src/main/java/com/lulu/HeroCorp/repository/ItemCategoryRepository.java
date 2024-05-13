package com.lulu.HeroCorp.repository;

import com.lulu.HeroCorp.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Integer> {

    //obtenir idCategory grace a name
    ItemCategory findByName(String name);
}
