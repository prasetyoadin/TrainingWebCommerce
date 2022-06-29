package com.domain.services;

import java.util.List;

import com.domain.models.entities.Supplier;

public interface SupplierService {
    
    public Supplier save(Supplier supplier);
    public Supplier findOne(Long id);
    public List<Supplier> findAll();
    public void removeOne(Long id);
}
