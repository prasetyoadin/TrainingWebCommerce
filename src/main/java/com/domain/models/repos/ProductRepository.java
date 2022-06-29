package com.domain.models.repos;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
    public List<Product> findByNameContains(String name);

    @Query("SELECT p FROM Product p WHERE p.name = :name ")
    public Product findByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE p.supplier = :supplier")
    public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);
    
}
