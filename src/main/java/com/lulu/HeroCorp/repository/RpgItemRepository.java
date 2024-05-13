package com.lulu.HeroCorp.repository;

import com.lulu.HeroCorp.model.RpgItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RpgItemRepository extends JpaRepository<RpgItem, Long> {

    List<RpgItem> findAllByIdCategory(long id);

    List<RpgItem> findAllByIdCategoryAndPriceLessThan(long id, double price);

    List<RpgItem> findAllByNameContaining(String name);

    List<RpgItem> findAllByEffect(String effect);
}
