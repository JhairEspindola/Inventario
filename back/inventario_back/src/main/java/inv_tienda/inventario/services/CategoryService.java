package inv_tienda.inventario.services;

import inv_tienda.inventario.DTO.CategoryRequest;
import inv_tienda.inventario.DTO.CategoryResponse;
import inv_tienda.inventario.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(CategoryRequest request);

    List<CategoryResponse> findAll();

    CategoryResponse findById(Long id);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);

    Category getEntity(Long id);

    void increaseStock(Long idCategoria, int quantity);

    void decreaseStock(Long idCategoria, int quantity);

    void applyStockDelta(Long idCategoria, int delta);
}
