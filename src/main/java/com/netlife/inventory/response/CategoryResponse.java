package com.netlife.inventory.response;

import com.netlife.inventory.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private List<Category> category;


}
