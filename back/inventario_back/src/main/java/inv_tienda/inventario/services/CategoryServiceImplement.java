package inv_tienda.inventario.services;

import inv_tienda.inventario.DTO.CategoryRequest;
import inv_tienda.inventario.DTO.CategoryResponse;
import inv_tienda.inventario.Exception.DuplicatedResourceException;
import inv_tienda.inventario.entity.Category;
import inv_tienda.inventario.mapper.CategoryMapper;
import inv_tienda.inventario.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplement implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse create(CategoryRequest request) {
        String normalizedName = request.name().trim();

        if (categoryRepository.existsByNameCategoryIgnoreCase(normalizedName)) {
            throw new
                    DuplicatedResourceException("Ya existe una categoria con el nombre" + normalizedName);
        }
        Category category = categoryMapper.toEntity(request);
        category.setNameCategory(normalizedName);

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponse(savedCategory);
    }
}
