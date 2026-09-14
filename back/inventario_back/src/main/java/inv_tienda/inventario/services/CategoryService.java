package inv_tienda.inventario.services;

import inv_tienda.inventario.DTO.CategoryRequest;
import inv_tienda.inventario.DTO.CategoryResponse;
import inv_tienda.inventario.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(CategoryRequest Request);

    List<CategoryResponse>findAll();

    CategoryResponse findById(Long id);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);

}
