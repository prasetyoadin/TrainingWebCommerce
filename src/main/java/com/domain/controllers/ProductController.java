package com.domain.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        } else {
            responseData.getMessages().add(0, "Product has been created");
            responseData.setStatus(true);
            responseData.setPayload(productService.save(product));
            return ResponseEntity.ok().body(responseData);
        }
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Product>> findOne(@PathVariable("id") Long id) {

        ResponseData<Product> responseData = new ResponseData<>();
        responseData.setPayload(productService.findOne(id));
        if (responseData.getPayload() == null) {
            responseData.getMessages().add(0, "Product doesn't exist");
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        } else {
            responseData.getMessages().add(0, "Product has been found");
            responseData.setStatus(true);
            return ResponseEntity.ok().body(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        } else {
            responseData.getMessages().add(0, "Product has been updated");
            responseData.setStatus(true);
            responseData.setPayload(productService.save(product));
            return ResponseEntity.ok().body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Product>> removeOne(@PathVariable("id") Long id) {
        ResponseData<Product> responseData = new ResponseData<>();
        responseData.setPayload(productService.findOne(id));
        if (responseData.getPayload() == null) {
            responseData.getMessages().add(0, "Product doesn't exist");
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        } else {
            productService.removeOne(id);
            responseData.getMessages().add(0, "Product has been deleted");
            responseData.setStatus(true);
            return ResponseEntity.ok().body(responseData);
        }
        
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId) {
        productService.addSupplier(supplier, productId);
    }

    @PostMapping("/search/name")
    public Product getProductByName(@RequestBody SearchData searchData) {
        return productService.findByName(searchData.getSearchKey());
    }

    @PostMapping("/search/nameLike")
    public List<Product> getProductByNameLike(@RequestBody SearchData searchData) {
        return productService.findByNameLike(searchData.getSearchKey());
    }

    @GetMapping("/search/category/{categoryId}")
    public List<Product> getProductByCategory(@PathVariable("categoryId") Long categoryId) {
        return productService.findByCategory(categoryId);
    }
}
