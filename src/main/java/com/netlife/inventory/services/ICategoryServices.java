package com.netlife.inventory.services;

import com.netlife.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryServices {

    public ResponseEntity<CategoryResponseRest> search();
}
