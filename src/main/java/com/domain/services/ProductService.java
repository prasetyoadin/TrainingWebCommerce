package com.domain.services;

import java.util.List;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;

public interface ProductService {
    
    public Product save(Product product);

    public Product findOne(Long id);

    public List<Product> findAll();

    public void removeOne(Long id);

    public List<Product> findByNameContains(String name);

    public void addSupplier(Supplier supplier, Long productId);

    public Product findByName(String name);

    public List<Product> findByNameLike(String name);

    public List<Product> findByCategory(Long categoryId);
}
