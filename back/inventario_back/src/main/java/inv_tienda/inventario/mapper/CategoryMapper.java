package inv_tienda.inventario.mapper;

import inv_tienda.inventario.DTO.CategoryRequest;
import inv_tienda.inventario.DTO.CategoryResponse;
import inv_tienda.inventario.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request){
        return Category.builder()
                .nameCategory(request.name())
                .description(request.description())
                .build();
    }

    public CategoryResponse toResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getIdCategory())
                .name(category.getNameCategory())
                .description(category.getDescription())
                .createdAt(category.getCreateAt())
                .updatedAt(category.getUpdateAt())
                .build();
    }
}