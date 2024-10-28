package com.Klaus.project.mapper;

import com.Klaus.project.model.dto.ProductResponse;
import com.Klaus.project.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.Klaus.project.utils.Constants.ACTIVE_STATUS;
import static com.Klaus.project.utils.Constants.INACTIVE_STATUS;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    @Mapping(target = "status", expression = "java(mapStatus(product))")
    ProductResponse toProductResponse(Product product);

    //Coneversion de string a boolean status
    default String mapStatus(Product product){
        return product.getStatus() ? ACTIVE_STATUS: INACTIVE_STATUS;
    }
}
