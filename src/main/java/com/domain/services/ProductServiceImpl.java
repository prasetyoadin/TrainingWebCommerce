package com.domain.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.models.repos.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findOne(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return productRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void removeOne(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByNameContains(String name) {
        return productRepository.findByNameContains(name);
    }

    @Override
    public void addSupplier(Supplier supplier, Long productId) {
        Product product = findOne(productId);
        if (product == null) {
            throw new RuntimeException("Product with ID: " + productId + " not found");
        } else {
            product.getSuppliers().add(supplier);
            save(product);
        }
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByNameLike(String name) {
        return productRepository.findProductByNameLike("%" +name+ "%");
    }

    @Override
    public List<Product> findByCategory(Long categoryId) {
        return productRepository.findProductByCategory(categoryId);
    }

}
