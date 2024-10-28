package com.Klaus.project.repository;

import com.Klaus.project.model.entity.Category;
import com.Klaus.project.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product>findAllByCategory(Category category);
}
