package com.Klaus.project.mapper;

import com.Klaus.project.model.dto.CategoryResponse;
import com.Klaus.project.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CategoryMapper {
    //@Mapping(target = 'name', source = 'nombre')
    CategoryResponse toCategoryResponse(Category category);


}
