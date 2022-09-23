package com.netlife.inventory.services;

import com.netlife.inventory.model.Category;
import com.netlife.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryServices {

    public ResponseEntity<CategoryResponseRest> search();

    public ResponseEntity<CategoryResponseRest> searchById(Long id);

    public ResponseEntity<CategoryResponseRest> save(Category category);

    public ResponseEntity<CategoryResponseRest> update(Category category, Long id);

}
