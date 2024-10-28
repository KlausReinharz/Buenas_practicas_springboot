package com.Klaus.project.controller;

import com.Klaus.project.model.dto.CreatedProductRequest;
import com.Klaus.project.model.dto.ProductResponse;
import com.Klaus.project.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> findAll(){
        return productService.findAll();
    }

    @GetMapping("{id}")
    public ProductResponse findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductResponse> findAllCategoryById(@PathVariable Long categoryId){
        return productService.findAllByCategory(categoryId);
    }

    @PostMapping
    public ResponseEntity<ProductResponse>save(@Valid @RequestBody CreatedProductRequest request){
        ProductResponse productResponse = productService.save(request);
        return ResponseEntity
                .created(URI.create("/api/products/"+ productResponse.getId()))
                .body(productResponse);
    }


    @PutMapping("{id}")
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody CreatedProductRequest request){
        return  productService.update(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

}
