package com.domain.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    
}
