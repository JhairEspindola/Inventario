package inv_tienda.inventario.mapper;

import inv_tienda.inventario.DTO.CategoryRequest;
import inv_tienda.inventario.DTO.CategoryResponse;
import inv_tienda.inventario.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request) {
        return Category.builder()
                .nameCategory(request.nombre())
                .descripcion(request.descripcion())
                .stock(request.stock() != null ? request.stock() : 0)
                .build();
    }

    public void updateEntity(Category category, CategoryRequest request) {
        category.setNameCategory(request.nombre().trim());
        category.setDescripcion(request.descripcion());
    }

    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getIdCategory())
                .nombre(category.getNameCategory())
                .descripcion(category.getDescripcion())
                .stock(category.getStock())
                .createdAt(category.getCreateAt())
                .updatedAt(category.getUpdateAt())
                .build();
    }
}
