package com.lulu.HeroCorp.control;

import com.lulu.HeroCorp.model.RpgItem;
import com.lulu.HeroCorp.model.RpgItemDTO;
import com.lulu.HeroCorp.service.RpgItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rpg/items")
public class RpgItemController {

    private final RpgItemService rpgItemService;

    public RpgItemController(RpgItemService rpgItemService) {
        this.rpgItemService = rpgItemService;
    }

    @GetMapping
    public ResponseEntity<List<RpgItem>> getAllItems() {
        List<RpgItem> items = rpgItemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<RpgItem>> getItems(
            @PathVariable String category,
            @RequestParam(value = "maxprice", required = false) Double maxprice
    ){
        List<RpgItem> items;

        if(maxprice != null) {
            items = rpgItemService.getItemsByCategoryUnderPrice(category, maxprice);
        }else{
            items = rpgItemService.getItemsByCategory(category);
        }

        if(items.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping(params = "like")
    public ResponseEntity<List<RpgItem>> getItemsLikePattern(
            @RequestParam(value="like", required=true) String like
    ){
        List<String> patterns = List.of(like.split(","));
        List<RpgItem> items = rpgItemService.getItemsNameContaining(patterns);

        if(items.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/noeffect")
    public ResponseEntity<List<RpgItem>> getItemsNoEffect(){
        List<RpgItem> items = rpgItemService.getItemsNoEffect();
        if (items.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RpgItem> createItem(@RequestBody RpgItemDTO rpgItemDTO){
        if (rpgItemDTO.isCreationValid()){
            RpgItem createdItem = rpgItemService.createItem(
                    rpgItemDTO.getName(),
                    rpgItemDTO.getCategory(),
                    rpgItemDTO.getPrice(),
                    rpgItemDTO.getEffect());
            return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RpgItem> modifyItem(
            @PathVariable Long id,
            @RequestBody RpgItemDTO rpgItemDTO
    ){
        RpgItem existingItem = rpgItemService.getItemById(id);
        if (existingItem == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(!rpgItemDTO.isModifyValid()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(rpgItemDTO.getName() != null) existingItem.setName(rpgItemDTO.getName());
        if(rpgItemDTO.getCategory() != null) existingItem.setIdCategory(rpgItemService.getIdCategoryByName(rpgItemDTO.getCategory()));
        if(rpgItemDTO.getPrice() != null) existingItem.setPrice(rpgItemDTO.getPrice());
        if(rpgItemDTO.getEffect() != null) existingItem.setEffect(rpgItemDTO.getEffect());

        RpgItem updatedItemInDb = rpgItemService.updateRpgItem(existingItem);
        return new ResponseEntity<>(updatedItemInDb, HttpStatus.OK);
    }
}

