package com.netlife.inventory.services;


import com.netlife.inventory.dao.ICategoryDao;
import com.netlife.inventory.model.Category;
import com.netlife.inventory.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicesImpl implements ICategoryServices{

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {

        CategoryResponseRest response = new CategoryResponseRest();
        try {

            List<Category> category = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategory(category);
            response.setMetadata("Respuesta ok","200","2022-09-21");

        }catch (Exception e){
            response.setMetadata("Respuesta failed","404","2022-09-21");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    /**
     * @param id 
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try {
            Optional<Category> category = categoryDao.findById(id);

            if (category.isPresent()){
                list.add(category.get());
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("Respuesta ok","200","2022-09-22");
            }else {
                response.setMetadata("Respuesta failed","500","2022-09-22");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            response.setMetadata("Respuesta failed","404","2022-09-22");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    /**
     * @param category
     * @return
     */
    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> save(Category category) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try {

            Category categorysave = categoryDao.save(category);

            if (categorysave != null){
                list.add(categorysave);
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("Respuesta ok","200","2022-09-22");
            }else {
                response.setMetadata("Not save category","404","2022-09-22");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            response.setMetadata("Respuesta failed Cant save category","404","2022-09-22");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    /**
     * @param category 
     * @param id
     * @return
     */
    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try {
            Optional<Category> categorySearch = categoryDao.findById(id);
            if(categorySearch.isPresent()){
                //Category will be updated
                categorySearch.get().setName(category.getName());
                categorySearch.get().setDescription(category.getDescription());

                Category categoryUpdate = categoryDao.save(categorySearch.get());

                if (categoryUpdate != null){
                    list.add(categoryUpdate);
                    response.getCategoryResponse().setCategory(list);
                    response.setMetadata("Update Category","200","2022-09-23");
                } else {
                    response.setMetadata("Category not update","401","2022-09-23");
                    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

            }else {
                response.setMetadata("Respuesta failed Category not found","401","2022-09-22");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            response.setMetadata("Respuesta failed Error Update Category","404","2022-09-22");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    /**
     * @param id 
     * @return
     */
    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deletebyid(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        try {

            categoryDao.deleteById(id);
            response.setMetadata("Category deleted","200","2022-09-23");

        }catch (Exception e){
            response.setMetadata("Failed to deleted","404","2022-09-23");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}
