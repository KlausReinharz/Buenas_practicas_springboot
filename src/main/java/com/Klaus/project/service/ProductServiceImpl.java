package com.Klaus.project.service;

import com.Klaus.project.exceptions.CategoryNotFoundException;
import com.Klaus.project.exceptions.ProductNotFoundException;
import com.Klaus.project.mapper.ProductMapper;
import com.Klaus.project.model.dto.CreatedProductRequest;
import com.Klaus.project.model.dto.ProductResponse;
import com.Klaus.project.model.entity.Product;
import com.Klaus.project.repository.CategoryRepository;
import com.Klaus.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findAllByCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(productRepository::findAllByCategory)
                .map(products -> products.stream()
                        .map(productMapper::toProductResponse)
                        .collect(Collectors.toList()))
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public ProductResponse save(CreatedProductRequest request) {
        return categoryRepository.findById(request.getCategoryId())
                .map(category -> {
                    Product product = new Product();
                    product.setName(request.getName());
                    product.setDescription(request.getDescription());
                    product.setPrice(request.getPrice());
                    product.setCategory(category);
                    product.setStatus(Boolean.TRUE);
                    return productRepository.save(product);
                })
                .map(productMapper::toProductResponse)
                .orElseThrow(CategoryNotFoundException::new);

    }

    @Override
    public ProductResponse update(Long id, CreatedProductRequest request) {
        return productRepository.findById(id)
                .map(product -> categoryRepository.findById(request.getCategoryId())
                        .map(category -> {
                            product.setName(request.getName());
                            product.setDescription(request.getDescription());
                            product.setPrice(request.getPrice());
                            product.setCategory(category);
                             return productRepository.save(product);
                        })
                        .orElseThrow(CategoryNotFoundException::new))
                .map(productMapper::toProductResponse)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(productRepository.findById(id).isEmpty()){
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(id);

    }
}
