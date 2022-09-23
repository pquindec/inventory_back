package com.netlife.inventory.controller;

import com.netlife.inventory.model.Category;
import com.netlife.inventory.response.CategoryResponseRest;
import com.netlife.inventory.services.ICategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    @Autowired
    private ICategoryServices service;

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategory(){
        ResponseEntity<CategoryResponseRest> response = service.search();

        return response;
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoryById(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.searchById(id);

        return response;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category){
        ResponseEntity<CategoryResponseRest> response = service.save(category);

        return response;
    }

    
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.update(category,id);

        return response;
    }

}
