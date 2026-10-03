package inv_tienda.inventario.services;

import inv_tienda.inventario.DTO.CategoryRequest;
import inv_tienda.inventario.DTO.CategoryResponse;
import inv_tienda.inventario.Exception.DuplicatedResourceException;
import inv_tienda.inventario.Exception.InsufficientStockException;
import inv_tienda.inventario.Exception.ResourceNotFoundException;
import inv_tienda.inventario.entity.Category;
import inv_tienda.inventario.mapper.CategoryMapper;
import inv_tienda.inventario.repositories.CategoryRepository;
import inv_tienda.inventario.repositories.CompraRepository;
import inv_tienda.inventario.repositories.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplement implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CompraRepository compraRepository;
    private final VentaRepository ventaRepository;

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        String normalizedName = request.nombre().trim();

        if (categoryRepository.existsByNameCategoryIgnoreCase(normalizedName)) {
            throw new DuplicatedResourceException("Ya existe una categoria con el nombre " + normalizedName);
        }

        Category category = categoryMapper.toEntity(request);
        category.setNameCategory(normalizedName);

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        return categoryMapper.toResponse(getEntity(id));
    }

    @Override
    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = getEntity(id);
        String normalizedName = request.nombre().trim();

        if (categoryRepository.existsByNameCategoryIgnoreCaseAndIdCategoryNot(normalizedName, id)) {
            throw new DuplicatedResourceException("Ya existe una categoria con el nombre " + normalizedName);
        }

        categoryMapper.updateEntity(category, request);
        category.setNameCategory(normalizedName);

        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = getEntity(id);

        if (compraRepository.existsByCategory_IdCategory(id) || ventaRepository.existsByCategory_IdCategory(id)) {
            throw new IllegalStateException(
                    "No se puede eliminar la categoria porque tiene compras o ventas asociadas");
        }

        categoryRepository.delete(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getEntity(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con id " + id));
    }

    @Override
    @Transactional
    public void increaseStock(Long idCategoria, int quantity) {
        Category category = getEntity(idCategoria);
        category.setStock(category.getStock() + quantity);
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void decreaseStock(Long idCategoria, int quantity) {
        Category category = getEntity(idCategoria);
        if (category.getStock() < quantity) {
            throw new InsufficientStockException(
                    "Stock insuficiente en la categoria " + category.getNameCategory()
                            + ". Disponible: " + category.getStock() + ", solicitado: " + quantity);
        }
        category.setStock(category.getStock() - quantity);
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void applyStockDelta(Long idCategoria, int delta) {
        if (delta > 0) {
            increaseStock(idCategoria, delta);
        } else if (delta < 0) {
            decreaseStock(idCategoria, -delta);
        }
    }
}
