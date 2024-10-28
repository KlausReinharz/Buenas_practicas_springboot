package com.Klaus.project.service;

import com.Klaus.project.model.dto.CreatedProductRequest;
import com.Klaus.project.model.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse findById(Long id);
    List<ProductResponse>findAll();
    List<ProductResponse>findAllByCategory(Long categoryId);
    ProductResponse save(CreatedProductRequest request);
    ProductResponse update(Long id, CreatedProductRequest request);
    void deleteById(Long id);

}
