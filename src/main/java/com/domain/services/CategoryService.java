package com.domain.services;

import java.util.List;

import com.domain.models.entities.Category;

public interface CategoryService {
    
    public Category save(Category category);
    public Category findOne(Long id);
    public List<Category> findAll();
    public void removeOne(long id);

}
